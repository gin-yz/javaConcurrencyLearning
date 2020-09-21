package com.cjs.javaConcurrencyLearning.singleModelLearn;

public class SingleInstanceHolderModel {
    private SingleInstanceHolderModel(){}

    private static class InstanceHolder {
        private final static SingleInstanceHolderModel instance = new SingleInstanceHolderModel();
    }

    public static SingleInstanceHolderModel getInstance(){
        return InstanceHolder.instance;
    }
}
