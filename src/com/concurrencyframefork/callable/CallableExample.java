package com.concurrencyframefork.callable;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableExample implements Callable<Integer> {

    public Integer call() throws Exception {
        Random generator = new Random();
        int randomNumber = generator.nextInt(5);

        Thread.sleep(randomNumber * 1000);

        return randomNumber;
    }
}
