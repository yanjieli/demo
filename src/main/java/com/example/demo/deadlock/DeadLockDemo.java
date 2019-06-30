package com.example.demo.deadlock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLockDemo implements Runnable {
    private int flag = 0;
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public DeadLockDemo(int flag) {
        super();
        this.flag = flag;
    }

    public static void main(String[] args) {
        DeadLockDemo d1 = new DeadLockDemo(0);
        DeadLockDemo d2 = new DeadLockDemo(1);
        new Thread(d1).start();
        new Thread(d2).start();
    }

    @Override
    public void run() {
        if (this.flag == 0) {
            synchronized (o1) {
                log.info("o1 coming");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("o1 o2 coming");
                }
            }
        } else {
            synchronized (o2) {
                log.info("o2 coming");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("o2 o1 coming");
                }
            }

        }

    }

}
