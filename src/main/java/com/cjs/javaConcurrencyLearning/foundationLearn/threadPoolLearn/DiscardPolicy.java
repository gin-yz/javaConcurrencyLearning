package com.cjs.javaConcurrencyLearning.foundationLearn.threadPoolLearn;

/**
 * Created by lizhuquan on 2018/3/20.
 */
public interface DiscardPolicy {
    void discard() throws DiscardException;
}
