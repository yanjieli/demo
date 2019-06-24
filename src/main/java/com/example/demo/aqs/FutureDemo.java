package com.example.demo.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FutureDemo {

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("Do something in callable");
                return "Done for callable";
            }
        });
        new Thread(futureTask).start();
        log.info("Do something in main");
        String result = null;
        try {
            result = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info("Result:{}", result);
    }

}
