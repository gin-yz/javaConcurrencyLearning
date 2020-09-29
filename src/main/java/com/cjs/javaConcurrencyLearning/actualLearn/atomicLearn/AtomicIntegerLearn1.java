package com.cjs.javaConcurrencyLearning.actualLearn.atomicLearn;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class AtomicIntegerLearn1 {
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static class addThread implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10000;i++){
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,100).mapToObj(new IntFunction<Thread>() {
            @Override
            public Thread apply(int value) {
                return new Thread(new addThread());
            }
        }).forEach(new Consumer<Thread>() {
            @Override
            public void accept(Thread thread) {
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(atomicInteger);
    }

}
