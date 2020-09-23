package com.cjs.javaConcurrencyLearning.foundationLearn.caughtExceptionLearn;

import java.util.concurrent.TimeUnit;

public class InsertHookThreadDemo {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println("hook thread1 is runing");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println("hook thread2 is runing");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
