package com.cjs.javaConcurrencyLearning.classLearn;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class PetersonDemo {
    private boolean[] flag = new boolean[2];
    private int victim;

    public void lock(int i) {
        flag[i] = true; // 告诉另一进程我要进入临界区
        victim = i; // 让对方先进入临界区
        while (flag[1-i] && victim == i){
        } // 等待对方离开临界区
    }

    public void unlock(int i) {
        flag[i] = false;
    }

    public static void main(String[] args) {
        PetersonDemo peterson = new PetersonDemo();

        IntStream.range(0,2).mapToObj(new IntFunction<Thread>() {
            @Override
            public Thread apply(int value) {
                return null;
            }
        });
    }

}

