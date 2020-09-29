package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.rateLimiterLearn;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimitDemo {
    private final static RateLimiter limiter = RateLimiter.create(2);//每秒两个请求

    public static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            limiter.acquire();
            new Thread(new Task()).start();
        }
    }
}
