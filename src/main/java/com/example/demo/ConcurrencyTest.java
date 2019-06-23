package com.example.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.demo.annoations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    public static int count = 0;
    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
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
        System.out.println("Count number:" + count);
    }

    private static void add() {
        count++;
    }

}
