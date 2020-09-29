package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.lockSupportLearn;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by 13 on 2017/5/5.
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if(Thread.interrupted()){
                    System.out.println(getName()+"被中断");
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        LockSupport.unpark(t1);
//        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
