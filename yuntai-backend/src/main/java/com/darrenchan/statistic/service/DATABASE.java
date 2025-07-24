package com.darrenchan.statistic.service;

public class DATABASE {
    //mysql相关配置
    public static String MYSQL_URL = "jdbc:mysql://localhost:3306/gmall_report?useSSL=false";
    public static String MYSQL_USERNAME = "root";
    public static String MYSQL_PASSWORD = "chen!!1992062812";
    public static String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    //clickhouse相关配置
    public static String CLICKHOUSE_URL = "jdbc:clickhouse://localhost:8123/default";
    public static String CLICKHOUSE_USERNAME = "chenchi";
    public static String CLICKHOUSE_PASSWORD = "chen!!1992062812";
    public static String CLICKHOUSE_DRIVER = "com.clickhouse.jdbc.ClickHouseDriver";
}
