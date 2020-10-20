package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.pubSubLearn;

import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;

public class PublisherDemo {

    public static void main(String[] args) {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        Mysubscriber<String> mysubscriber = new Mysubscriber<>();
        Mysubscriber<String> mysubscriber2 = new Mysubscriber<>();

        publisher.subscribe(mysubscriber);
        publisher.subscribe(mysubscriber2);

        System.out.println("Publishing data items...");

        String[] items = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        Arrays.stream(items).forEach(i -> {
            publisher.submit(i);
            System.out.println(Thread.currentThread().getName() + " publish" + i);
        });

        publisher.close();

        try {
            synchronized ("A") {
                "A".wait();
            }
        } catch (InterruptedException ie) {

        }
    }
}
