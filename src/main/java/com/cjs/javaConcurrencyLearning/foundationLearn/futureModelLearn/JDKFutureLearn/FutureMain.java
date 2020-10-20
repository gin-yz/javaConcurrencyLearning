package com.cjs.javaConcurrencyLearning.foundationLearn.futureModelLearn.JDKFutureLearn;

import java.util.concurrent.*;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new RealData("cjs"));

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(futureTask);
        Future<String> futureTask2 = threadPool.submit(new RealData("cjs1"));

        threadPool.shutdown();

        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());
    }
}
