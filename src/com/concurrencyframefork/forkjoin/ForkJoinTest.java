package com.concurrencyframefork.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinTest extends RecursiveTask<Long> {

    private final long[] NUMBERS;
    private final int START;
    private final int END;
    private static final long THRESHOLD = 10_000;

    private ForkJoinTest(long[] NUMBERS) {
        this(NUMBERS, 0, NUMBERS.length);
    }

    private ForkJoinTest(long[] numbers, int start, int end) {
        this.NUMBERS = numbers;
        this.START = start;
        this.END = end;
    }

    @Override
    protected Long compute() {

        int length = END - START;
        if (length <= THRESHOLD) {
            return add();
        }

        ForkJoinTest firstTask = new ForkJoinTest(NUMBERS, START, START + length / 2);
        firstTask.fork();

        ForkJoinTest secondTask = new ForkJoinTest(NUMBERS, START + length / 2, END);

        Long secondTaskResult = secondTask.compute();
        Long firstTaskResult = firstTask.join();

        return firstTaskResult + secondTaskResult;
    }

    private long add() {
        long result = 0;
        for (int i = START; i < END; i++) {
            result += NUMBERS[i];
        }
        return result;
    }

    public static long startForkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinTest(numbers);

        return new ForkJoinPool().invoke(task);
    }
}
