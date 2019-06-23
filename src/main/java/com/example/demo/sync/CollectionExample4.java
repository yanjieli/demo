package com.example.demo.sync;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectionExample4 {
    public static final int THREAD_NUMBER = 200;
    public static final int COUNT_NUMBER = 5000;
    // private static List<Integer> list = Collections.synchronizedList(new
    // ArrayList<>());
    private static Set<Integer> list = Collections.synchronizedSet(Sets.newHashSet());

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(COUNT_NUMBER);
        Semaphore seamphore = new Semaphore(THREAD_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < COUNT_NUMBER; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    seamphore.acquire();
                    updateCount(count);
                    seamphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    Thread.currentThread().interrupt();
                }
                cdl.countDown();
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        executorService.shutdown();
        log.info("Count size:{}", list.size());
    }

    private static void updateCount(int i) {
        list.add(i);
    }

}
