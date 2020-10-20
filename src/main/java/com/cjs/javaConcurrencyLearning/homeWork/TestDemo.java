package com.cjs.javaConcurrencyLearning.homeWork;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestDemo {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);

        if (atomicBoolean.getAndSet(false)){
            System.out.println(atomicBoolean.get());
        }
    }
}
