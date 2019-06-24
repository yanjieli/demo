package com.example.demo.aqs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForkJoinDemo extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD = 2;
    private int end;
    private int start;

    public ForkJoinDemo(int start, int end) {
        this.end = end;
        this.start = start;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (end + start) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(start, middle);
            ForkJoinDemo rightTask = new ForkJoinDemo(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResut = rightTask.join();

            sum = leftResult + rightResut;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(1, 100);
        Future<Integer> result = forkJoinPool.submit(task);
        int resultSum = 0;
        try {
            resultSum = result.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error:{}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info("The result:{}", resultSum);

    }

}
