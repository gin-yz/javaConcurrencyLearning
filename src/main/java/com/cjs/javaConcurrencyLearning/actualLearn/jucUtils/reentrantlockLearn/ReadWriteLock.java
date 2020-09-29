package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.reentrantlockLearn;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class ReadWriteLock {
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();
    private int value;

    public void readValue() {

        try {
            readLock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " get read Lock,and value is:" + this.value);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.lock();
        }

    }

    public void writeValue(int value) {
        try {
            writeLock.lock();
            this.value = value;
            System.out.println(Thread.currentThread().getName() + " get write Lock,and modify value to:" + value);
            TimeUnit.SECONDS.sleep(1);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();



        //写进程
        Random random = new Random();
        IntStream.range(0, 20).mapToObj(value -> {
            return new Thread(()->{ readWriteLock.writeValue(random.nextInt()); },"WriteThread-" + value);
        }).forEach(Thread::start);

        //读进程
        IntStream.range(0, 20).mapToObj(new IntFunction<Thread>() {
            @Override
            public Thread apply(int value) {
                return new Thread(readWriteLock::readValue, "ReadThread-" + value);
            }
        }).forEach(Thread::start);
    }

}
