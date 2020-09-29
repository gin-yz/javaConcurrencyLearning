package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.pubSubLearn;

import java.util.concurrent.Flow;

public class Mysubscriber<T> implements Flow.Subscriber<T> {
    private Flow.Subscription subscription; //Flow.Subscription.request(int n) 设定请求的消息个数

    //订阅者注册后被调用的第一个方法
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
        System.out.println(Thread.currentThread().getName()+" onSubscribe");
    }

    //当有下一个数据项准备好的时候，进行通知
    @Override
    public void onNext(T item) {
        System.out.println(Thread.currentThread().getName()+" Received:"+item);
        subscription.request(1);
    }

    //异常时调用
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        synchronized ("A"){
            "A".notifyAll();
        }
    }

    //没有数据时调用
    @Override
    public void onComplete() {
        System.out.println("Done");
        synchronized ("A"){
            "A".notifyAll();
        }
    }
}
