package com.example.demo.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.demo.annoations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class ConcurrencyAtomicTest2 {

    public static AtomicInteger count = new AtomicInteger(0);
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
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
                countdownlatch.countDown();
            });
        }
        countdownlatch.await();
        exeService.shutdown();
        log.error("Count number:" + count.get());
    }

    private static void add() {
        count.incrementAndGet();
    }

}
