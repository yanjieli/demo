package com.example.demo.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.demo.MyException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierDemo {

    private static final int THREADNUM = 10;
    private static CyclicBarrier cyc = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADNUM; i++) {
            final int countNumber = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    updateValue(countNumber);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
        }
        executorService.shutdown();
    }

    private static void updateValue(int countNumber) throws MyException {
        try {
            Thread.sleep(1000);
            log.info("{} ready", countNumber);
            cyc.await();
            log.info("{} continue", countNumber);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
            throw new MyException(e.getMessage());
        }
    }
}
