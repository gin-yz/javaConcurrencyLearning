/*
* 可中断锁
* */

package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.reentrantlockLearn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class EnableInterreptLock implements Runnable {
    private final ReentrantLock lock1;
    private final ReentrantLock lock2;

    private final int i;

    public EnableInterreptLock(int i, ReentrantLock lock1, ReentrantLock lock2) {
        this.i = i;
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        try {
            if (i == 1) {
                lock1.lockInterruptibly();
                TimeUnit.SECONDS.sleep(1);
                lock2.lockInterruptibly();
            }else {
                lock2.lockInterruptibly();
                TimeUnit.SECONDS.sleep(1);
                lock1.lockInterruptibly();
            }

            System.out.println("thread id is:"+this.i+" and do some work");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }

            System.out.println("lock num is :"+this.i+"and finish");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        EnableInterreptLock run1 = new EnableInterreptLock(1,lock1,lock2);
        EnableInterreptLock run2 = new EnableInterreptLock(2,lock1,lock2);

        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);

        t1.start();
        t2.start();

        t2.interrupt();

    }
}
