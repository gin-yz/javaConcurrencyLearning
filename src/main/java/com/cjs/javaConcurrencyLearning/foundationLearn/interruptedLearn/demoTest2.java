package com.cjs.javaConcurrencyLearning.foundationLearn.interruptedLearn;

import java.util.concurrent.TimeUnit;

public class demoTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    TimeUnit.MINUTES.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        thread.interrupt();

        while (true){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(thread.getState());
            System.out.println(thread.isInterrupted());
        }
    }
}
