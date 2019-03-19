package com.concurrencyframefork.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolApplication {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            Task task = new Task("Task " + i);
            System.out.println("Created : " + task.getName());

            executor.execute(task);
        }
        executor.shutdown();
    }
}
