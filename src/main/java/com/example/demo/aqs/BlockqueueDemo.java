package com.example.demo.aqs;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockqueueDemo {
    private static BlockingQueue<String> blockqueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    Thread.currentThread().interrupt();
                }
                blockqueue.add("a");
                log.info("Add blockqueue size:{}", blockqueue.size());
            }
        }).start();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    Thread.currentThread().interrupt();
                }
                blockqueue.poll();
                log.info("Remove blockqueue size:{}", blockqueue.size());
            }
        }).start();

    }

}
