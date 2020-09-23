package com.cjs.javaConcurrencyLearning.foundationLearn.caughtExceptionLearn;

public class caughtExceptionDemo {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.printf("thread name:%s,result:%s",t.getName(),e);
            }
        });

        new Thread(()->{
            System.out.println(1/0);

        },"MyThread").start();


    }
}
