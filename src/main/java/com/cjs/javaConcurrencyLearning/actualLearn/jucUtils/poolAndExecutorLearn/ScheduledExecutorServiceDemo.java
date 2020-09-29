/*
* 以上一个任务开始时，也就是一定间隔时间调度一个任务，不管上一个任务是否完成
* 如果执行周期小于线程的运行时间，则线程结束后立即调用．
* */
package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.poolAndExecutorLearn;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 13 on 2017/5/5.
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS); //ｐｅｒｉｏｄ:执行周期两秒,initialDelay:立即调用
    }
}
