package com.example.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Description: Quart定时任务配置（也可以在yml配置文件里配置）
 * @Author: crx
 * @Create: 10:24 2021/4/30
 */
@Configuration
public class QuartScheduleConfig {

    @Bean("quartScheduleBean")
    @Primary
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(dataSource);
        //quart参数
        Properties properties = new Properties();
        properties.put("org.quartz.scheduler.instanceName", "MyScheduler");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");
        // 线程池配置
        properties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount", "20");
        properties.put("org.quartz.threadPool.threadPriority", "5");
        properties.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", true);
        // JobStore配置
        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        properties.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        // 集群配置
        properties.put("org.quartz.jobStore.isClustered", "true");
        properties.put("org.quartz.jobStore.clusterCheckinInterval", "10000");
        properties.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        properties.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
        factoryBean.setQuartzProperties(properties);
        return factoryBean;
    }
}
