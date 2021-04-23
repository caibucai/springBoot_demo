package com.example.demo.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: jkd线程池
 * @Author: crx
 * @Create: 14:22 2021/4/20
 */
@Configuration
public class JavaAsyncConfig {

    @Bean("javaExecutor")
    public ExecutorService getJavaPool() {
        TaskThreadFactory threadFactory = new TaskThreadFactory("java", false, 1);
        return new ThreadPoolExecutor(
                10,
                20,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
