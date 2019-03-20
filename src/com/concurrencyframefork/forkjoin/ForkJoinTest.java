package com.concurrencyframefork.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinTest extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    private static final long THRESHOLD = 10_000;

    private ForkJoinTest(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinTest(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        int length = end - start;
        if (length <= THRESHOLD) {
            return add();
        }

        ForkJoinTest firstTask = new ForkJoinTest(numbers, start, start + length / 2);
        firstTask.fork();

        ForkJoinTest secondTask = new ForkJoinTest(numbers, start + length / 2, end);

        Long secondTaskResult = secondTask.compute();
        Long firstTaskResult = firstTask.join();

        return firstTaskResult + secondTaskResult;
    }

    private long add() {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += numbers[i];
        }
        return result;
    }

    public static long startForkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinTest(numbers);

        return new ForkJoinPool().invoke(task);
    }
}
