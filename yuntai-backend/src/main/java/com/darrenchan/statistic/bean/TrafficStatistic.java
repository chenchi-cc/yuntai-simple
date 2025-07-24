package com.darrenchan.statistic.bean;

public class TrafficStatistic {
    private String dt;  //日期
    private Integer recentDays;  //最近多少天的数据
    private String channel;  //渠道
    private Integer uvCount;  //独立访客数
    private Integer avgDurationSec; //平均访问时长（秒）
    private Integer avgPageCount; //平均访问页面数
    private Integer svCount; //访问次数
    private Double bounceRate; //跳出率

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Integer getRecentDays() {
        return recentDays;
    }

    public void setRecentDays(Integer recentDays) {
        this.recentDays = recentDays;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getUvCount() {
        return uvCount;
    }

    public void setUvCount(Integer uvCount) {
        this.uvCount = uvCount;
    }

    public Integer getAvgDurationSec() {
        return avgDurationSec;
    }

    public void setAvgDurationSec(Integer avgDurationSec) {
        this.avgDurationSec = avgDurationSec;
    }

    public Integer getAvgPageCount() {
        return avgPageCount;
    }

    public void setAvgPageCount(Integer avgPageCount) {
        this.avgPageCount = avgPageCount;
    }

    public Integer getSvCount() {
        return svCount;
    }

    public void setSvCount(Integer svCount) {
        this.svCount = svCount;
    }

    public Double getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(Double bounceRate) {
        this.bounceRate = bounceRate;
    }
}
