package com.example.demo.service.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.example.demo.service.IAsyncService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: crx
 * @Create: 10:26 2021/4/20
 */
@Service
@Slf4j
public class AsyncServiceImpl implements IAsyncService {
    @Autowired
    private ExecutorService executorService;

    @Autowired
    private Executor executor;

    @SneakyThrows
    @Override
    @Async("javaExecutor")
    public void getAsync1() {
        long l = System.currentTimeMillis();
        Thread.sleep(2000);
        log.info("getAsync1:{},threadId:{}", System.currentTimeMillis() - l, Thread.currentThread().getName());
    }

    @SneakyThrows
    @Override
    @Async("javaExecutor")
    public Future<Integer> getAsync2() {
        long l = System.currentTimeMillis();
        Thread.sleep(1000);
        AsyncResult<Integer> getAsync2 = new AsyncResult<>(2);
        log.info("getAsync2:{},threadId:{}", System.currentTimeMillis() - l, Thread.currentThread().getName());
        return getAsync2;
    }

    @SneakyThrows
    @Override
    @Async("javaExecutor")
    public FutureTask<Integer> getAsync3() {
        long l = System.currentTimeMillis();
        Thread.sleep(2000);
        FutureTask<Integer> task = new FutureTask<>(() -> 3);
        executor.execute(task);
        log.info("getAsync3:{},threadId:{}", System.currentTimeMillis() - l, Thread.currentThread().getName());
        return task;
    }

    @SneakyThrows
    @Override
    @Async("springExecutor")
    public FutureTask<Integer> getAsync4() {
        long l = System.currentTimeMillis();
        Thread.sleep(1000);
        FutureTask<Integer> task = new FutureTask<>(() -> 4);
        executorService.submit(task);
//        executorService.submit(() -> 4);
        log.info("getAsync3:{},threadId:{}", System.currentTimeMillis() - l, Thread.currentThread().getName());
        return task;
    }
}
