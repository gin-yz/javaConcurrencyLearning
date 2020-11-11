//自实现读写锁接口
package com.cjs.javaConcurrencyLearning.homeWork;

public interface SelfReadWriteLock {
    SelfLock getWriterLock(); //拿到读锁
    SelfLock getReaderLock(); //拿到写锁
}
