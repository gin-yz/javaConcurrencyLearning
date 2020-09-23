/*
* 使用内部类实现单例模式线程安全
* */

package com.cjs.javaConcurrencyLearning.foundationLearn.singleModelLearn;

public class SingleInstanceHolderModel {
    private SingleInstanceHolderModel(){}

    private static class InstanceHolder {
        private final static SingleInstanceHolderModel instance = new SingleInstanceHolderModel();
    }

    public static SingleInstanceHolderModel getInstance(){
        return InstanceHolder.instance;
    }
}
