package com.cjs.javaConcurrencyLearning.threadDesignModel.observerModel;

/**
 * 线程事件
 *
 * @author tuyrk
 */
public class RunnableEvent {
    private final RunnableState state;
    private final Thread thread;
    private final Throwable cause;

    public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
        this.state = state;
        this.thread = thread;
        this.cause = cause;
    }

    public RunnableState getState() {
        return state;
    }

    public Thread getThread() {
        return thread;
    }

    public Throwable getCause() {
        return cause;
    }
}
