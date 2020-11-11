//自实现锁
package com.cjs.javaConcurrencyLearning.homeWork;

import java.util.ArrayList;
import java.util.List;


public class SelfBooleanLock implements SelfLock {
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>(); //阻塞列表
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
    public void unlock() {
        synchronized (this) {
            if (this.runCurrentThread == Thread.currentThread()) {
                this.locked = false;
                this.notifyAll();
            }
        }
    }

}
