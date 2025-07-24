package com.darrenchan.report.service;

import com.darrenchan.statistic.service.DATABASE;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateTableService {
    public static String createMySQLTable(String sql) {
        try {
            var connection = DriverManager.getConnection(
                    DATABASE.MYSQL_URL,
                    DATABASE.MYSQL_USERNAME,
                    DATABASE.MYSQL_PASSWORD
            );
            var createTableStatement = connection.prepareStatement(sql);
            createTableStatement.execute();
            createTableStatement.close();
            connection.close();

            return "create table success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "create table error";
        }
    }

    public static String createClickHouseTable(String sql) {
        try {
            var connection = DriverManager.getConnection(
                    DATABASE.CLICKHOUSE_URL,
                    DATABASE.CLICKHOUSE_USERNAME,
                    DATABASE.CLICKHOUSE_PASSWORD
            );
            var createTableStatement = connection.prepareStatement(sql);
            createTableStatement.execute();
            createTableStatement.close();
            connection.close();

            return "create table success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "create table error";
        }
    }
}
