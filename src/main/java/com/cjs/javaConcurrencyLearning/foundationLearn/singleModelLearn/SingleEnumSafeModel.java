/*
* 使用枚举类型实现单例模式线程安全
* 原因：枚举类有且只会加载一次
* */
package com.cjs.javaConcurrencyLearning.foundationLearn.singleModelLearn;

public class SingleEnumSafeModel {

    private SingleEnumSafeModel() {}

    private enum Singleton{
        INSTANCE;

        private final SingleEnumSafeModel instance;

        private Singleton(){
            instance = new SingleEnumSafeModel();
        }

        public SingleEnumSafeModel getInnerInstance(){
            return this.instance;
        }
    }

    public static SingleEnumSafeModel getInstance(){
        return Singleton.INSTANCE.getInnerInstance();
    }
}
