package com.concurrencyframefork.scheduledfuture;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public interface Dealyed extends Comparable<Delayed> {
    long getDelay(TimeUnit unit);
}
