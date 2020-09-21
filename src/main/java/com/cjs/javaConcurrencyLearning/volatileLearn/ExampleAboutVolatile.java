package com.cjs.javaConcurrencyLearning.volatileLearn;

import java.util.concurrent.TimeUnit;

public class ExampleAboutVolatile {

    private static int INT_VALUE = 0;
    private static final int MAX_VALUE = 10;

    public static void main(String[] args) {

        //这个哥们的ＩＮＴ_VALUE一直使用自己ｃｐｕ的ｃａｃｈｅ的ＩＮＴ_VALUE，导致一直为原来的值
        new Thread(() -> {
            int localValue = INT_VALUE;
            while (localValue < MAX_VALUE) {
                if (localValue != INT_VALUE) {
                    System.out.println("READER localValue is:" + localValue + " and INT_VALUE is:" + INT_VALUE);
                    localValue = INT_VALUE;
                }

            }

        }, "READER").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int localValue = INT_VALUE;
                while (localValue < MAX_VALUE) {
                    INT_VALUE++;
                    localValue = INT_VALUE;
                    System.out.println("WRITER localValue is:" + localValue + " and INT_VALUE is:" + INT_VALUE);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "WRITER").start();
    }
}
