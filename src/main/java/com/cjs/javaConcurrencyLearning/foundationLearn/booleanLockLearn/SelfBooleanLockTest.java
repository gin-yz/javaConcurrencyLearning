package com.cjs.javaConcurrencyLearning.foundationLearn.booleanLockLearn;


import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

public class SelfBooleanLockTest {
    private final SelfLock lock = new SelfBooleanLock();

    public void syncMethod(){
        try {
            lock.lock();
            int randomTime = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread());
            TimeUnit.SECONDS.sleep(randomTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //超时测试
    public void syncMethodTimeOut(long mills){
        try {
            lock.lock(mills);
            int randomTime = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread());
            TimeUnit.SECONDS.sleep(randomTime);

        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SelfBooleanLockTest selfBooleanLockTest = new SelfBooleanLockTest();
        IntStream.range(0,10).mapToObj(i-> new Thread(selfBooleanLockTest::syncMethod)).forEach(Thread::start);

        new Thread(()->{
            selfBooleanLockTest.syncMethodTimeOut(1000);
        }).start();

        System.out.println(selfBooleanLockTest.lock.getBolckedThreads());
    }
}
