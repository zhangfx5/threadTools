package com.ambitious.tools;

import com.sun.deploy.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ambitious on 2018/8/3.
 */
public class ThreadUtil {
    private static Map<String, ExecutorService> executorPool = new ConcurrentHashMap<String, ExecutorService>();

    public static void registerExecutor(String executorName, ExecutorService executorService) {
        if (executorName == null || executorService == null) {
            return;
        }
        if (executorService.isShutdown() || executorService.isTerminated()) {
            executorPool.put(executorName, executorService);
            return;
        }
        ExecutorService oldExecutor = executorPool.get(executorName);
        if (oldExecutor.isShutdown() || oldExecutor.isTerminated()) {
            executorPool.put(executorName, executorService);
            return;
        }
    }

    public static <T> TaskFuture<T> addTask(String executorName, ThreadToolJob<T> taskCallable) {
        return new TaskFuture<T>(executorPool.get(executorName).submit(new TaskCallable<T>(taskCallable)));
    }


}
