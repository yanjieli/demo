package com.example.demo.notthreadsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.demo.annoations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class ConcurrencyAtomicTest4 { // public static SimpleDateFormat simpleDateFormat = new
    // SimpleDateFormat("yyyymmdd");
    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService exeService = Executors.newCachedThreadPool();
        final CountDownLatch countdownlatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            exeService.execute(() -> {
                try {
                    semaphore.acquire();
                    upateTime();
                    semaphore.release();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                countdownlatch.countDown();
            });
        }
        countdownlatch.await();
        exeService.shutdown();
        log.info("success!");
    }

    private static void upateTime() {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
        try {
            date = simpleDateFormat.parse("20190621");
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        // log.info("" + date.getTime());
    }
}
