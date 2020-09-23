/*
* 其他线程出现饥饿
* */

package com.cjs.javaConcurrencyLearning.foundationLearn.synchronizedLearn;

import java.util.concurrent.TimeUnit;

public class ExampleLearn implements Runnable {
    private int index = 0;
    private final static int MAX = 5000000;
    private final static Object MUTEX = new Object();

    @Override
    public void run() {
        synchronized (MUTEX){
            while (index<MAX){
                System.out.println("线程是："+Thread.currentThread().getName()+",index为："+this.index++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExampleLearn exampleLearn = new ExampleLearn();

        Thread thread1 = new Thread(exampleLearn, "cjs1");
        Thread thread2 = new Thread(exampleLearn, "cjs2");
        Thread thread3 = new Thread(exampleLearn, "cjs3");
        Thread thread4 = new Thread(exampleLearn, "cjs4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println(thread2.getState());


    }
}
