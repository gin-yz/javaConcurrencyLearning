package com.cjs.javaConcurrencyLearning.homeWork;

import java.util.concurrent.TimeUnit;

public class TestDemo2 {


    public static void main(String[] args) throws InterruptedException {
        final Integer[] x = {0,1,2,3,4,5,6,7};
        new Thread(){
            @Override
            public void run() {
                while (true){
                    for (Integer integer : x) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(integer);
                    }
                }
            }
        }.start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(){
            @Override
            public void run() {
                x[0] =10;
                x[1] =20;
                x[2] =30;
                x[3] =40;
                x[4] =50;
            }
        }.start();
    }
}
