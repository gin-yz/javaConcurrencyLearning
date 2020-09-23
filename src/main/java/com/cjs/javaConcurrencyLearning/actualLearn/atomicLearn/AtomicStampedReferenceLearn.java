/*
* 使用时间撮解决CAS带来的ＡＢＡ问题
* */

package com.cjs.javaConcurrencyLearning.actualLearn.atomicLearn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceLearn {
    private static final AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,0);

    public static void main(String[] args) {

        new Thread(() -> {
            // 第一次拿到的时间戳
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+" 第1次时间戳："+stamp+" 值为："+atomicStampedReference.getReference());
            // 休眠5s,确保t2执行完ABA操作
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            // t2将时间戳改为了3,cas失败
            boolean b = atomicStampedReference.compareAndSet(1, 10, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+" CAS是否成功："+b);
            System.out.println(Thread.currentThread().getName()+" 当前最新时间戳："+atomicStampedReference.getStamp()+" 最新值为："+atomicStampedReference.getReference());
        },"t1").start();

        // t2进行ABA操作
        new Thread(() -> {
            // 第一次拿到的时间戳
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+" 第1次时间戳："+stamp+" 值为："+atomicStampedReference.getReference());
            // 休眠，修改前确保t1也拿到同样的副本，初始值为1
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            // 将副本改为20，再写入，紧接着又改为1，写入，每次提升一个时间戳，中间t1没介入
            atomicStampedReference.compareAndSet(1, 20, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+" 第2次时间戳："+atomicStampedReference.getStamp()+" 值为："+atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(20, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+" 第3次时间戳："+atomicStampedReference.getStamp()+" 值为："+atomicStampedReference.getReference());

        },"t2").start();
    }
}
