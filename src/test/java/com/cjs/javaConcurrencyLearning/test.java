package com.cjs.javaConcurrencyLearning;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class test {
    public static void main(String[] args) {

        FastPath fastPath = new FastPath();
        IntStream.range(0, 16).mapToObj(new IntFunction<Thread>() {
            @Override
            public Thread apply(int value) {
                return new Thread(() -> {
                    fastPath.lock();
                    System.out.println(Thread.currentThread().getName()+" in");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        fastPath.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println(Thread.currentThread().getName()+" out");
                    }

                },"thread-"+String.valueOf(value));
            }
        }).forEach(Thread::start);

    }
}

class FastPath {

    private static final Lock lock = new ReentrantLock();
    private long x, y = -1;

    public void lock() {
        long i = Thread.currentThread().getId();
        x = i; // I’m here
        while (y != -1) {
        } // is the lock free?
        y = i; // me again?
//        if (x != i) // Am I still here?
            lock.lock(); // slow path
    }

    public void unlock() {
        y = -1;
        lock.unlock();
    }
}

