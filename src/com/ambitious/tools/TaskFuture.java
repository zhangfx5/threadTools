package com.ambitious.tools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by ambitious on 2018/8/3.
 */
public class TaskFuture<T> {

    private Future<T> future;

    public TaskFuture(Future<T> future) {
        this.future = future;
    }

    public T get() {
        try {
            return future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
