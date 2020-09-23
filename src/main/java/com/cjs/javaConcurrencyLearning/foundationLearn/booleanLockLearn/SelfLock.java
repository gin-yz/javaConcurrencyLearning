package com.cjs.javaConcurrencyLearning.foundationLearn.booleanLockLearn;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface SelfLock {
    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBolckedThreads();
}
