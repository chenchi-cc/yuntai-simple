package com.darrenchan.scheduler.bean;

//调度的任务详情，每个任务详情都会保存在mysql中
//对应yuntai_schedule.scheduler_job_info
public class SchedulerJobInfo {
    private Long id; //主键id
    private String jobType; //任务类型，1.简单任务，2.字段空值率监控任务

    private String jobName; //任务名称
    private String jobGroup; //任务组名
    private String jobStatus; //任务状态，运行中，暂停中
    private String jobClass;
    private String cronExpression; //cron表达式，如果是cron任务则有值
    private String description;
    private String interfaceName;
    private Integer repeatTime; //重复时间，如果是简单任务则有值
    private Boolean cronJob; //是否是cron任务

    public SchedulerJobInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Integer getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(Integer repeatTime) {
        this.repeatTime = repeatTime;
    }

    public Boolean getCronJob() {
        return cronJob;
    }

    public void setCronJob(Boolean cronJob) {
        this.cronJob = cronJob;
    }
}
