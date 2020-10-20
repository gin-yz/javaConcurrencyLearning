package com.cjs.javaConcurrencyLearning.foundationLearn.futureModelLearn.JDKFutureLearn;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i =0;i<10;i++){
            stringBuffer.append(para);
            TimeUnit.MILLISECONDS.sleep(100);
        }

        return stringBuffer.toString();
    }
}
