package com.example.demo.notthreadsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.example.demo.annoations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class ConcurrencyAtomicTest5 {

    public static DateTimeFormatter datetimeformatter = DateTimeFormat.forPattern("yyyymmdd");
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
                    log.info(e.getMessage());
                }
                countdownlatch.countDown();
            });
        }
        countdownlatch.await();
        exeService.shutdown();
    }

    private static void upateTime() {
        DateTime datetime = datetimeformatter.parseDateTime("20190621");
    }

}
