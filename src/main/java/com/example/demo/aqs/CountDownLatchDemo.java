package com.example.demo.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountDownLatchDemo {

    private static final int THREADNUM = 100;

    public static void main(String[] args) throws Exception {
        CountDownLatch countdownlatch = new CountDownLatch(THREADNUM);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADNUM; i++) {
            final int countNumber = i;
            executorService.execute(() -> {
                try {
                    updateValue(countNumber);
                } catch (Exception e) {
                    log.error(e.getMessage());
                } finally {
                    countdownlatch.countDown();
                }
            });
        }
        try {
            countdownlatch.await();
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        executorService.shutdown();

    }

    private static void updateValue(int countNumber) throws MyException {
        try {
            Thread.sleep(100);
            log.info("{}", countNumber);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
            throw new MyException(e.getMessage());
        }
    }
}

class MyException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public MyException(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }

}