package com.darrenchan.admin.util;

import com.darrenchan.admin.service.DATABASE;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DataSourceProvider {
    private static final HikariDataSource dataSource;
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DATABASE.URL);
        config.setUsername(DATABASE.USERNAME);
        config.setPassword(DATABASE.PASSWORD);
        config.setDriverClassName(DATABASE.DRIVER);
        // 可选优化
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(30000);
        config.setValidationTimeout(5000);
        dataSource = new HikariDataSource(config);
    }
    public static DataSource getDataSource() {
        return dataSource;
    }
}
