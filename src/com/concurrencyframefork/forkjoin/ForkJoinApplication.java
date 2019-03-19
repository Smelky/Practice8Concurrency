package com.concurrencyframefork.forkjoin;

public class ForkJoinApplication {
    public static void main(String[] args) {
        System.out.println(ForkJoinTest.startForkJoinSum(1_000_000));
    }
}
