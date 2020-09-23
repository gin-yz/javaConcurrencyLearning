package com.cjs.javaConcurrencyLearning.foundationLearn.interruptedLearn;

import java.util.concurrent.TimeUnit;

public class demo1Test {

    public static void main(String[] args) {
        Thread.currentThread().interrupt();

        System.out.println("hehe1");

        System.out.println("hehe2");


        System.out.println(Thread.currentThread().isInterrupted());

        try{
            TimeUnit.MINUTES.sleep(1);

        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("hehe3");
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getState());
        }


    }

}
