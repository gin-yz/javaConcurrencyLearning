package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.reentrantlockLearn;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 implements Runnable {
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static int i;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            reentrantLock.lock();
            try {
                i++;
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo1 lockDemo1 = new ReentrantLockDemo1();

        Thread t1 = new Thread(lockDemo1);
        Thread t2 = new Thread(lockDemo1);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
