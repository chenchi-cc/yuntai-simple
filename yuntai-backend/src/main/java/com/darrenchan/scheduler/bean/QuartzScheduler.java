package com.darrenchan.scheduler.bean;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

//单例模式，整个spring boot服务中只有一个Quartz调度器实例
public class QuartzScheduler {
    private static Scheduler scheduler = null;

    public static Scheduler getInstance() throws Exception {
        if (scheduler == null) {
            var factory = new StdSchedulerFactory();
            scheduler = factory.getScheduler();
        }

        return scheduler;
    }
}
