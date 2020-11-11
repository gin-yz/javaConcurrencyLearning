//自实现锁接口
package com.cjs.javaConcurrencyLearning.homeWork;

public interface SelfLock {
    void lock() throws InterruptedException; //加锁

    void unlock() throws InterruptedException; //解锁

}
