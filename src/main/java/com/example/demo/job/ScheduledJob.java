package com.example.demo.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: @Scheduled注解实现的定时任务
 * @Author: crx
 * @Create: 16:10 2021/4/23
 */
@Component
public class ScheduledJob {

//    @Scheduled(cron = "*/1 * * * * ?")
//    @Async("javaExecutor")
    public void job1() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("job1：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + "；name：" + Thread.currentThread().getName());
    }

//    @Scheduled(fixedDelay = 1000)
//    @Async("javaExecutor")
    public void job2() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("job2：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + "；name：" + Thread.currentThread().getName());
    }

//    @Scheduled(fixedRate = 1000)
//    @Async("javaExecutor")
    public void job3() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("job3：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + "；name：" + Thread.currentThread().getName());
    }
}
