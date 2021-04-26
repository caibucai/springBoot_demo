package com.example.demo.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description:
 * @Author: crx
 * @Create: 14:57 2021/4/25
 */
public class QuartzJob1 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("QuartzJob1：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + "；name：" + Thread.currentThread().getName());
    }
}
