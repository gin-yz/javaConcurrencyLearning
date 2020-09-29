package com.cjs.javaConcurrencyLearning.threadDesignModel.observerModel;

import java.util.stream.IntStream;

/**
 * 线程生命周期观察者测试类
 *
 * @author tuyrk
 */
public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        ThreadLifeCycleObserver observer = new ThreadLifeCycleObserver();

        IntStream.range(0,1000).forEach(id -> new Thread(new ObservableRunnable(observer) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id " + id);
                    Thread.sleep(1000L);
//                     int x = 1 / 0;
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, String.valueOf(id)).start());
    }
}
