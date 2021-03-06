/*
* 此方法可能导致控制异常，不推荐使用
* 原因：有可能一个线程ｎｅｗ完了之后，其实ｎｅｗ里面创建的对象并没有创建好，另外一个线程拿到实例的时候是一个没有创建好的实例．
* 解决方法：加volatile或者instanceHolder
* */

package com.cjs.javaConcurrencyLearning.foundationLearn.singleModelLearn;

public class SynSingleModel {
    private static SynSingleModel synSingleModelInstance; //加volatite

    private SynSingleModel() {
    }

    public static SynSingleModel getInstance() {
        if (synSingleModelInstance == null) {
            synchronized (SynSingleModel.class) {
                if (synSingleModelInstance == null) {
                    synSingleModelInstance = new SynSingleModel();//加ｖｏｌｉｔｉｌｅ之后，执行到这里，可以保证ｎｅｗ对象里面的操作一定创建好了
                }
            }
        }
        return SynSingleModel.synSingleModelInstance;

    }

    public void doSomething(){
    }
}
