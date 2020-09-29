package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.longAddrLearn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAdderDemo {
//    private LongAdder longAdder = new LongAdder();
//    private static final int MAX_VALUE = Integer.MAX_VALUE;
//
//    public class LongAddrThread implements Runnable{
//        @Override
//        public void run() {
//            while (longAdder.sum()<MAX_VALUE){
//                longAdder.increment();
//            }
//        }
//    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1000);

        LongAdder longAdder = new LongAdder();
        longAdder.add(10000);
        IntStream.range(0,1000).mapToObj((i)->{
            return new Thread(){
                @Override
                public void run() {
                    super.run();
                    longAdder.increment();
                    countDownLatch.countDown();
                }
            };
        }).forEach(Thread::start);
        countDownLatch.await();

        System.out.println(longAdder.sum());
    }
}
