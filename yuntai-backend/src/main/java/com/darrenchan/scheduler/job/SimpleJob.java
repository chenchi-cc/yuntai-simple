package com.darrenchan.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

// 简单任务示例
public class SimpleJob implements Job {
    public SimpleJob() {}

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务被触发执行！");
    }
}
