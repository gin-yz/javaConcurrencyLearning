/*
 * 使用CyclicBarrier,线程一同执行完，才继续
 * */


package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.cyclicBarrierLearn;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class demo1 {
    public static void main(String[] args) throws InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("All Tasks has finish");
            }
        });  //定义两个线程都要调用await方法才行．

        new Thread(){
            @Override
            public void run() {
                System.out.println("T1 start");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
                    System.out.println("T1 finish");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                System.out.println("T2 start");
                try {
                    TimeUnit.SECONDS.sleep(10);
                    cyclicBarrier.await();
                    System.out.println("T2 finish");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());

//        cyclicBarrier.reset();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());

        System.out.println("main finish");

    }
}
