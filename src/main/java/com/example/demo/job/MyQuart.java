package com.example.demo.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: crx
 * @Create: 15:04 2021/4/25
 */
public class MyQuart {
    @Autowired
    private Scheduler scheduler;

    public static void main(String[] args) throws SchedulerException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 2、创建JobDetail实例，并与PrinQuartzJob1类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob1.class).withIdentity("job1", "group1").build();

        // 3、构建Trigger实例,每隔1s执行一次
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

        //4、执行，将trigger和jobDetail加入这个调度器
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
