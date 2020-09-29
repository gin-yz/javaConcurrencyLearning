package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.reentrantlockLearn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockNoWait implements Runnable{
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if(lock.tryLock()){
                System.out.println("thrend name is"+Thread.currentThread().getName()+" and do some work");
                TimeUnit.SECONDS.sleep(3);
            }else {
                System.out.println("thrend name is"+Thread.currentThread().getName()+" and fail to get lock");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
                System.out.println("thrend name is"+Thread.currentThread().getName()+" release lock");

            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TryLockWait tryLockWait = new TryLockWait();

        Thread t1 = new Thread(tryLockWait,"thread1");
        Thread t2 = new Thread(tryLockWait,"thread2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

