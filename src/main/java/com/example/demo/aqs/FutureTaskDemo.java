package com.example.demo.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FutureTaskDemo {

    static class MyCallable implements Callable<String> {

        private String name;
        private int age;

        public MyCallable(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            log.info("Do something in callable!");
            return "Done for" + name + ":" + age;
        }
    }

    public static void main(String[] args) {

        int age = 30;
        String name = "小花";
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(new MyCallable(name, age));
        log.info("Do something in main");
        String result = null;
        try {
            result = (String) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("Result:{}", result);
        executorService.shutdown();
    }

}
