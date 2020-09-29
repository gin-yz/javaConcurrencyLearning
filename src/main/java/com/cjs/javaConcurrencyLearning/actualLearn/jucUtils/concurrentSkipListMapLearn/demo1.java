/*
* 跳表，在存储数据大小有序的时候,并发，非常有用
* */

package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.concurrentSkipListMapLearn;


import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class demo1 {

    public static void main(String[] args) {
        Map<Integer,Integer> map = new ConcurrentSkipListMap<>();

        for(int i =0;i<30;i++){
            map.put(i,i);
        }

        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
