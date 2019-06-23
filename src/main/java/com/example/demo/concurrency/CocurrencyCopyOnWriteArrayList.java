package com.example.demo.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.demo.annoations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class CocurrencyCopyOnWriteArrayList {

    private static List<Integer> array = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        int clientTotal = 5000;
        int threadTotal = 200;

        ExecutorService exeService = Executors.newCachedThreadPool();
        final CountDownLatch countdownlatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            exeService.execute(() -> {
                try {
                    semaphore.acquire();
                    upateTime(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                countdownlatch.countDown();
            });
        }
        countdownlatch.await();
        exeService.shutdown();
        log.info("size is:{}", array.size());
    }

    private static void upateTime(int i) {
        array.add(i);
    }

}
