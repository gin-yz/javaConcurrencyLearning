/*
* 在一般的ReetrantLock中，若两个线程同时申请一把锁，系统会随机挑选一个线程进行运行．
* 这会导致饥饿，将ReetrantLock(true)，为ＦＩＦＯ的方式给申请的线程进行服务
* */
package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.reentrantlockLearn;

import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockWithJustice implements Runnable{
    private final static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName()+" has obtain lock");
            }finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReetrantLockWithJustice reetrantLockWithJustice = new ReetrantLockWithJustice();

        Thread t1 = new Thread(reetrantLockWithJustice,"t1");
        Thread t2 = new Thread(reetrantLockWithJustice,"t2");

        //两个线程会交替输出
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
