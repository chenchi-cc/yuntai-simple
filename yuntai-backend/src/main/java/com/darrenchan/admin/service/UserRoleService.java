package com.darrenchan.admin.service;

import com.darrenchan.admin.util.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;

public class UserRoleService {
    private static final DataSource ds = DataSourceProvider.getDataSource();
    /**
     * 根据用户ID获取角色ID
     */
    public static Long getRoleIdByUserId(Long userId) {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var selectStatement = connection.prepareStatement(
                    "SELECT role_id FROM user_role WHERE user_id = ?"
            );
            selectStatement.setLong(1, userId);
            var resultSet = selectStatement.executeQuery();
            Long roleId = null;
            if (resultSet.next()) {
                roleId = resultSet.getLong("role_id");
            }

            selectStatement.close();
            connection.close();
            return roleId;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 添加用户和角色的关系
     */
    public static void addUserIdRoleIdRelationship(Long userId, Long roleId) {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var deleteStatement = connection.prepareStatement(
                    "DELETE FROM user_role WHERE user_id = ?"
            );
            deleteStatement.setLong(1, userId);
            deleteStatement.execute();

            var insertStatement = connection.prepareStatement(
                    "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)"
            );
            insertStatement.setLong(1, userId);
            insertStatement.setLong(2, roleId);
            insertStatement.execute();

            deleteStatement.close();
            insertStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
