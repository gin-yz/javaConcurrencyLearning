package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.pubSubLearn.dataLink;

import com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.pubSubLearn.Mysubscriber;

import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;

public class TestDemo {
    public static void main(String[] args) {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        Mysubscriber<String> mysubscriber = new Mysubscriber<>();
        Mysubscriber<String> mysubscriber2 = new Mysubscriber<>();

        TransformProcessor<String,String> toUpperCase = new TransformProcessor<>(String::toUpperCase);
        TransformProcessor<String,String> toLowerCase = new TransformProcessor<>(String::toLowerCase);

        publisher.subscribe(toLowerCase);
        publisher.subscribe(toUpperCase);

        toLowerCase.subscribe(mysubscriber);
        toUpperCase.subscribe(mysubscriber2);

        System.out.println("Publishing data items...");

        String[] items = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        Arrays.asList(items).stream().forEach(i -> {
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
