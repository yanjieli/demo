package com.example.demo.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.example.demo.annoations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class ReentrantLockDemo {
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

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

    private static void add() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

}
