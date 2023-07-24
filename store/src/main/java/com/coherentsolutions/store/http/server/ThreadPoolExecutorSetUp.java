package com.coherentsolutions.store.http.server;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorSetUp {
    int corePoolSize = 10;
    int maximumPoolSize = 100;
    long keepAliveTime = 60L;

    public Executor createThreadPoolExecutor() {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, java.util.concurrent.TimeUnit.SECONDS, new java.util.concurrent.LinkedBlockingQueue<>());
    }
}
