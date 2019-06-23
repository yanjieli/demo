package com.example.demo.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.demo.annoations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class SynchronizedDemo {
    private static int count = 0;

    public static void main(String[] args) throws Exception {

        int clientTotal = 5000;
        int threadTotal = 200;
        ExecutorService exeService = Executors.newCachedThreadPool();
        CountDownLatch countdownlatch = new CountDownLatch(clientTotal);
        Semaphore semaphore = new Semaphore(threadTotal);
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
        log.info("Count number:{}", count);
    }

    private static synchronized void add() {
        count++;
    }

}
