package com.example.demo.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorServiceDemo4 {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        service.schedule(() -> {
            log.info("{}", System.currentTimeMillis());
        }, 1, TimeUnit.SECONDS);

        log.info("finished");
        service.shutdown();
    }

}
