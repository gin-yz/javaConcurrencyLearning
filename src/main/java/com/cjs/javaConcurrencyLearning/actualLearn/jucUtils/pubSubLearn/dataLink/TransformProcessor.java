package com.cjs.javaConcurrencyLearning.actualLearn.jucUtils.pubSubLearn.dataLink;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class TransformProcessor<T, R> extends SubmissionPublisher<R> implements Flow.Processor<T, R> {
    private final Function<? super T, ? extends R> function;
    private Flow.Subscription subscription;

    public TransformProcessor(Function<? super T, ? extends R> function) {
        this.function = function;
    }


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        this.submit(this.function.apply(item));
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}
