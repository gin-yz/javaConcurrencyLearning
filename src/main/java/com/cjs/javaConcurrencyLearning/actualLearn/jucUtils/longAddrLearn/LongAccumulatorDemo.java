package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.longAddrLearn;

import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {
    public static void main(String[] args) {
        //挑选最大的
        LongAccumulator longAccumulatorSelectMAX = new LongAccumulator(Long::max,Long.MAX_VALUE);
        //累加
        LongAccumulator longAccumulatorAdd = new LongAccumulator(Long::sum,0L);
        //除了要自定义规则,其他的和ＬｏｎｇＡｄｄｅｒ差不多

    }
}
