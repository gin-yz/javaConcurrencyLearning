package com.cjs.javaConcurrencyLearning.foundationLearn.booleanLockLearn;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;


public class SelfBooleanLock implements SelfLock {
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();
    private Thread runCurrentThread;

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (this.locked) {
                try {
                    this.blockedList.add(Thread.currentThread());
                    this.wait();
                }catch (InterruptedException e){
                    this.blockedList.remove(Thread.currentThread());
                    throw e;
                }
            }
            this.blockedList.remove(Thread.currentThread());
            this.locked = true;
            this.runCurrentThread = Thread.currentThread();
        }

    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainTime = mills;
                long endTime = System.currentTimeMillis() + mills;

                while (this.locked) {
                    if (remainTime <= 0) throw new TimeoutException("can not get lock during :" + mills);

                    try {
                        this.blockedList.add(Thread.currentThread());
                        this.wait();
                    }catch (InterruptedException e){
                        this.blockedList.remove(Thread.currentThread());
                        throw e;
                    }

                    remainTime = endTime - System.currentTimeMillis();
                }

                this.blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.runCurrentThread = Thread.currentThread();
            }
        }

    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (this.runCurrentThread == Thread.currentThread()) {
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBolckedThreads() {
        return Collections.unmodifiableList(this.blockedList);
    }
}
