/*
* 拷贝ｇｒｏｕｐ下的线程,复制ＴｈｒｅａｄＧｒｏｕｐ同理
* */

package com.cjs.javaConcurrencyLearning.foundationLearn.ThreadGroupLearn;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class copyThreadInGroup {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup myGroup = new ThreadGroup("MyGroup");

        Thread thread = new Thread(myGroup,new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"MyThread");
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();


        Thread[] threadArray = new Thread[mainGroup.activeCount()]; //activeCount()获取ｇｒｏｕｐ下所有的线程数量，包括子ｇｒｏｕｐ，但是不准确


        int recurseSize = mainGroup.enumerate(threadArray,true); //递归的取，会拷贝所有的线程，包括子ｇｒｏｕｐ的线程

        System.out.println(recurseSize);

        Arrays.stream(threadArray).map(new Function<Thread, String>() {
            @Override
            public String apply(Thread thread) {
                return thread.getName();
            }
        }).forEach(System.out::println);


    }
}

