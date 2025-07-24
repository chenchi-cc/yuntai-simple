package com.darrenchan.statistic.bean;

public class PagePath {
    private String source;  //来源页面
    private String target;  //目标页面
    private Integer value;  //路径值，表示从source到target的访问量或其他统计值

    public PagePath() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
