package com.darrenchan.admin.service;

import com.darrenchan.admin.bean.Role;
import com.darrenchan.admin.util.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleService {
    private static final DataSource ds = DataSourceProvider.getDataSource();
    /**
     * 获取所有角色
     */
    public static List<Role> getAllRoles() {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var selectStatement = connection.prepareStatement(
                    "SELECT id, role_name FROM role"
            );
            var roles = new ArrayList<Role>();
            var resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                var role = new Role();
                role.setId(resultSet.getLong("id"));
                role.setRoleName(resultSet.getString("role_name"));
                roles.add(role);
            }
            selectStatement.close();
            connection.close();
            return roles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据用户ID获取对应的角色名称
     */
    public static String getUserRoleName(Long userId) {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var selectStatement = connection.prepareStatement(
                    "SELECT role.role_name AS roleName FROM user_role" +
                            "  INNER JOIN role ON user_role.role_id = role.id" +
                            "  WHERE user_role.user_id = ?"
            );
            selectStatement.setLong(1, userId);
            var resultSet = selectStatement.executeQuery();
            var roleName = "";
            //一个用户只有一个角色
            if (resultSet.next()) {
                roleName = resultSet.getString("roleName");
            }

            selectStatement.close();
            connection.close();
            return roleName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 删除角色
     */
    public static void deleteRole(Long roleId) {
        Connection connection = null;
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);


            //关闭自动提交
            connection.setAutoCommit(false);

            //删除操作放在一个事务中
            var deleteRoleStatement = connection.prepareStatement(
                    "DELETE FROM role WHERE id = ?"
            );
            deleteRoleStatement.setLong(1, roleId);
            deleteRoleStatement.execute();

            var deleteRolePermissionStatement = connection.prepareStatement(
                    "DELETE FROM role_permission WHERE role_id = ?"
            );
            deleteRolePermissionStatement.setLong(1, roleId);
            deleteRolePermissionStatement.execute();

            var deleteUserRoleStatement = connection.prepareStatement(
                    "DELETE FROM user_role WHERE role_id = ?"
            );
            deleteUserRoleStatement.setLong(1, roleId);
            deleteUserRoleStatement.execute();

            connection.commit();

            deleteRoleStatement.close();
            deleteRolePermissionStatement.close();
            deleteUserRoleStatement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Error deleting role, rolling back");
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // 恢复自动提交
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加新角色
     */
    public static void addRole(String roleName) {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var insertStatement = connection.prepareStatement(
                    "INSERT INTO role (role_name) VALUES (?)"
            );
            insertStatement.setString(1, roleName);
            insertStatement.execute();

            insertStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
