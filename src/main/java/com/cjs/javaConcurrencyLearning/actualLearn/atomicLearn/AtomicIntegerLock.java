package com.cjs.javaConcurrencyLearning.actualLearn.atomicLearn;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerLock {
    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockThread;

    public void tryLock() throws GetLockExpection {
        boolean success = value.compareAndSet(0, 1);

        if (!success) {
            throw new GetLockExpection("uncaught lock");
        } else lockThread = Thread.currentThread();
    }

    public void unLock() {
        if (value.get() == 0) {
            return;
        }
        if (lockThread.equals(Thread.currentThread())) {
            value.compareAndSet(1, 0);
        }
    }

}
