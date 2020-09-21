/*
* 用volitile修饰的变量不是原子性的，如下
* 两个线程会读取相同的INT_VALUE变量
* */

package com.cjs.javaConcurrencyLearning.volatileLearn;

import java.util.concurrent.TimeUnit;

public class NotAtomicAboutVolatile {

    private static final int MAX_VALUE = 10_000;
    private static volatile int INT_VALUE = 0;

    public static void main(String[] args) {
        new Thread(()->{
            while (INT_VALUE<=MAX_VALUE){
                System.out.println(Thread.currentThread().getName()+":"+INT_VALUE++);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"WRITER1").start();

        new Thread(()->{
            while (INT_VALUE<=MAX_VALUE){
                System.out.println(Thread.currentThread().getName()+":"+INT_VALUE++);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"WRITER2").start();
    }
}
