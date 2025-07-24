package com.darrenchan.government.bean;

/**
 * 用来保存hive表的元数据分析结果
 */
public class GovernmentDetail {
    private Long id; //主键id
    private Long tableId; //表id
    private String tableName; //表名
    private String databaseName; //数据库名
    private Boolean hasTableComment; //是否有表注释
    private Integer fieldsNumber; //字段数量
    private Integer missingCommentFieldsNumber; //缺少注释的字段数量
    private Boolean hasTechnicalOwner; //是否有技术负责人
    private Boolean hasBusinessOwner;  //是否有业务负责人
    private Boolean hasOutputLastSevenDay;  //是否有最近7天的输出

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Boolean getHasTableComment() {
        return hasTableComment;
    }

    public void setHasTableComment(Boolean hasTableComment) {
        this.hasTableComment = hasTableComment;
    }

    public Integer getFieldsNumber() {
        return fieldsNumber;
    }

    public void setFieldsNumber(Integer fieldsNumber) {
        this.fieldsNumber = fieldsNumber;
    }

    public Integer getMissingCommentFieldsNumber() {
        return missingCommentFieldsNumber;
    }

    public void setMissingCommentFieldsNumber(Integer missingCommentFieldsNumber) {
        this.missingCommentFieldsNumber = missingCommentFieldsNumber;
    }

    public Boolean getHasTechnicalOwner() {
        return hasTechnicalOwner;
    }

    public void setHasTechnicalOwner(Boolean hasTechnicalOwner) {
        this.hasTechnicalOwner = hasTechnicalOwner;
    }

    public Boolean getHasBusinessOwner() {
        return hasBusinessOwner;
    }

    public void setHasBusinessOwner(Boolean hasBusinessOwner) {
        this.hasBusinessOwner = hasBusinessOwner;
    }

    public Boolean getHasOutputLastSevenDay() {
        return hasOutputLastSevenDay;
    }

    public void setHasOutputLastSevenDay(Boolean hasOutputLastSevenDay) {
        this.hasOutputLastSevenDay = hasOutputLastSevenDay;
    }
}
