//自实现读写锁
package com.cjs.javaConcurrencyLearning.homeWork;

public class SelfSimpleReadWriteLock implements SelfReadWriteLock {

    private int readers; //读者数量

    private boolean writer; //写者是否拿到锁

    private final SelfLock writerLock; //写者锁

    private final SelfLock readLock; //读者锁

    private final SelfLock LOCK; //内部锁

    private final Object condition; //condition变量

    //初始化
    public SelfSimpleReadWriteLock() {
        this.readers = 0;
        this.writer = false;
        this.condition = new Object();
        this.LOCK = new SelfBooleanLock();
        this.writerLock = new WriterLock();
        this.readLock = new ReadLock();
    }

    //javaBean
    @Override
    public SelfLock getWriterLock() {
        return this.writerLock;
    }

    @Override
    public SelfLock getReaderLock() {
        return this.readLock;
    }

    //读者锁
    private class ReadLock implements SelfLock {
        @Override
        public void lock() throws InterruptedException {
            LOCK.lock();
            try {
                while (SelfSimpleReadWriteLock.this.writer)
                    SelfSimpleReadWriteLock.this.condition.wait();
                SelfSimpleReadWriteLock.this.readers += 1;
            } finally {
                LOCK.unlock();
            }
        }

        @Override
        public void unlock() throws InterruptedException {
            LOCK.lock();
            try {
                SelfSimpleReadWriteLock.this.readers -= 1;
                if (SelfSimpleReadWriteLock.this.readers == 0)
                    SelfSimpleReadWriteLock.this.condition.notifyAll();
            } finally {
                LOCK.unlock();
            }
        }
    }

    //写者锁
    private class WriterLock implements SelfLock {
        @Override
        public void lock() throws InterruptedException {
            LOCK.lock();
            try {
                while (SelfSimpleReadWriteLock.this.readers != 0 || SelfSimpleReadWriteLock.this.writer)
                    SelfSimpleReadWriteLock.this.condition.wait();
                SelfSimpleReadWriteLock.this.writer = true;
            } finally {
                LOCK.unlock();
            }
        }

        @Override
        public void unlock() {
            SelfSimpleReadWriteLock.this.writer = false;
            SelfSimpleReadWriteLock.this.condition.notifyAll();
        }

    }

}
