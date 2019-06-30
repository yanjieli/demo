package com.example.demo.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorServiceDemo3 {

    public static void main(String[] args) throws Exception {

        ExecutorService service = Executors.newSingleThreadExecutor();
        final CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            service.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error("{}", index);
                }
                log.info("{}", index);
                cdl.countDown();
            });
        }

        try {
            cdl.await();
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        service.shutdownNow();
    }

}
