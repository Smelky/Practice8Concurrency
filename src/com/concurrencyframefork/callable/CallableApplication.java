package com.concurrencyframefork.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableApplication {
    public static void main(String[] args) throws Exception {
        FutureTask[] randomNumberTasks = new FutureTask[5];

        for (int i = 0; i < 5; i++) {
            Callable callable = new CallableExample();
            randomNumberTasks[i] = new FutureTask(callable);
            Thread randomNumbers = new Thread(randomNumberTasks[i]);
            randomNumbers.start();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(randomNumberTasks[i].get());
        }
    }
}
