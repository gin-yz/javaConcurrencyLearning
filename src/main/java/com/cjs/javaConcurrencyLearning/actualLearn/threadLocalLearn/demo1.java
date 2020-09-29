package com.cjs.javaConcurrencyLearning.actualLearn.threadLocalLearn;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class demo1 {
    public static void main(String[] args) {
        IntStream.range(0,30).mapToObj(new IntFunction<Thread>() {
            @Override
            public Thread apply(int value) {
                return new Thread(()->{
                    int a = value;
                    System.out.println(a);
                },String.valueOf(value));
            }
        });
    }
}
