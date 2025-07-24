package com.darrenchan.scheduler.bean;

//对mysql某个字段空值率的监控结果
public class MonitorDetail {
    private String databaseName; //数据库名称
    private String tableName; //表名称
    private String fieldName; //字段名称
    private Double fieldNullRate; //字段空值率

    public MonitorDetail() {
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getFieldNullRate() {
        return fieldNullRate;
    }

    public void setFieldNullRate(Double fieldNullRate) {
        this.fieldNullRate = fieldNullRate;
    }

    @Override
    public String toString() {
        return "MonitorDetail{" +
                "databaseName='" + databaseName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldNullRate=" + fieldNullRate +
                '}';
    }
}
