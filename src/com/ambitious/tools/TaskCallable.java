package com.ambitious.tools;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ambitious on 2018/8/3.
 */
public class TaskCallable<T> implements Callable {

    private CountDownLatch countDown;
    private ThreadToolJob job;

    public TaskCallable(ThreadToolJob job) {
        this.job = job;
    }

    public TaskCallable(CountDownLatch countDown, ThreadToolJob job) {
        this.countDown = countDown;
        this.job = job;
    }

    @Override
    public T call() throws Exception {
        try {
            return (T) job.execute();
        } catch (Throwable e) {
            System.out.println("异常信息" + e.getMessage());
        } finally {
            if (countDown != null) {
                countDown.countDown();
            }
        }
        return null;
    }
}
