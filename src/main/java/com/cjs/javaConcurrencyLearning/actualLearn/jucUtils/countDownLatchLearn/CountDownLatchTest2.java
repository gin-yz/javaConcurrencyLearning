/*
* 一个进程进行到一半工作等待，需要请求另外一个进程，另外一个进程先要进行初始化的工作，然后处理进程请求
* 可以使用wait和notify实现，也可以使用CountDownLatch
* 如果其中一个进程中断，没有调用countdown,那么可以中断ａｗａｉｔ的线程
* */

package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.countDownLatchLearn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(){
            @Override
            public void run() {
                super.run();

                System.out.println("wating other thread to handle my task");

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println("anthor thread to handle task");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }

            }
        }.start();
    }
}
