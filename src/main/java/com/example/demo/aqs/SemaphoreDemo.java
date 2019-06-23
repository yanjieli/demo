package com.example.demo.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.demo.MyException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreDemo {

    private static final int THREADNUM = 20;
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADNUM; i++) {
            final int countNumber = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    updateValue(countNumber);
                    semaphore.release();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
        }
        executorService.shutdown();
    }

    private static void updateValue(int countNumber) throws MyException {
        try {
            log.info("update {}", countNumber);
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
            throw new MyException(e.getMessage());
        }
    }
}
