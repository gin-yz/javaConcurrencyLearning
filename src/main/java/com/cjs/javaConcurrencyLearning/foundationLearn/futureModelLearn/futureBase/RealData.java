package com.cjs.javaConcurrencyLearning.foundationLearn.futureModelLearn.futureBase;

/**
 * Created by 13 on 2017/5/8.
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String param) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(param);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = stringBuffer.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
