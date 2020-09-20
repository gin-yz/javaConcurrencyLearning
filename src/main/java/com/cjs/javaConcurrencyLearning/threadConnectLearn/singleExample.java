/*
* 执行会报错
* */

package com.cjs.javaConcurrencyLearning.threadConnectLearn;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class singleExample {
    public static void main(String[] args) {
        EventQueue eventQueue = new EventQueue();


        new Thread(() -> {
            for (int i = 0; ; i++) {
                try {
                    eventQueue.setLinkedList();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread1").start();

        new Thread(() -> {
            for (int i = 0; ; i++) {
                try {
                    eventQueue.setLinkedList();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread2").start();

        new Thread(() -> {
            for (int i = 0; ; i++) {
                try {
                    eventQueue.getLinkedList();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }
}

class EventQueue {
    private final LinkedList<String> linkedList = new LinkedList<>();
    private final int max = 10;

    public void setLinkedList() throws InterruptedException {
        synchronized (linkedList) {
            if (linkedList.size() >= max) {  //ｉｆ改成ｗｈｉｌｅ可解决
                linkedList.wait();
            }

            System.out.println(Thread.currentThread().getName()+",push:" + linkedList.size());
            linkedList.add(String.valueOf(linkedList.size()));
            linkedList.notify(); //多线程使用ｎｏｔｉｆｙＡｌｌ

        }
    }

    public void getLinkedList() throws InterruptedException {
        synchronized (linkedList) {
            if (linkedList.isEmpty()) { //ｉｆ改成ｗｈｉｌｅ可解决
                linkedList.wait();
            }

            System.out.println("pop,name:" + linkedList.poll());
            linkedList.notify();  //多线程使用ｎｏｔｉｆｙＡｌｌ
        }
    }
}
