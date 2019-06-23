package com.example.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.example.demo.annoations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程之间除了同步互斥，还要考虑通信。在Java5之前我们的通信方式为：wait 和
 * notify。Condition的优势是支持多路等待，即可以定义多个Condition，每个condition控制线程的一条执行通路。传统方式只能是一路等待
 * 
 * @author yanjieli
 *
 */
@Slf4j
@ThreadSafe
public class StampLockDemo {
    private static int count = 0;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                lock.lock();
                log.info("waitting signal");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal");
            lock.unlock();
        }).start();
        new Thread(() -> {
            lock.lock();
            log.info("get lock");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // condition.signal();
            condition.signalAll();
            log.info("send signal");
            lock.unlock();
        }).start();
    }
}
