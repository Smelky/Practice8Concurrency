package com.concurrencyframefork.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorApplication {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(() -> System.out.println("I'm Runnable task."));
        Future<Integer> futureTask1 = executor.submit(() -> {
            System.out.println("I'm Callable task.");
            return 1 + 1;
        });

        try {
            otherTask("Before Future Result");
            Integer result = futureTask1.get(5, TimeUnit.SECONDS);
            System.out.println("Get future result : " + result);
            otherTask("After Future Result");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static void otherTask(String name) {
        System.out.println("I'm other task! " + name);
    }
}
