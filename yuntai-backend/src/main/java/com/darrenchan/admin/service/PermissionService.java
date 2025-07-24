package com.darrenchan.admin.service;

import com.darrenchan.admin.bean.Permission;
import com.darrenchan.admin.util.DataSourceProvider;
import com.darrenchan.admin.util.PermissionHelper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionService {
    private static final DataSource ds = DataSourceProvider.getDataSource();
    /**
     * 获取所有权限
     */
    public static List<Permission> getAllPermissions() {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var selectStatement = connection.prepareStatement(
                    "SELECT id, parent_id, permission_name, permission_code FROM permission"
            );
            var resultSet = selectStatement.executeQuery();
            var permissions = new ArrayList<Permission>();
            while (resultSet.next()) {
                var permission = new Permission();
                permission.setId(resultSet.getLong("id"));
                permission.setParentId(resultSet.getLong("parent_id"));
                permission.setPermissionName(resultSet.getString("permission_name"));
                permission.setPermissionCode(resultSet.getString("permission_code"));
                permissions.add(permission);
            }
            permissions = PermissionHelper.build(permissions);

            selectStatement.close();
            connection.close();
            return permissions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据用户ID获取用户对应的所有权限
     */
    public static List<String> getPermissionByUserId(Long userId) {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var selectStatement = connection.prepareStatement(
                    """
                    SELECT permission.permission_code AS permissionCode FROM user
                    INNER JOIN user_role
                    INNER JOIN role_permission
                    INNER JOIN permission
                    ON user.id = user_role.user_id
                    AND user_role.role_id = role_permission.role_id
                    AND role_permission.permission_id = permission.id
                    WHERE user.id = ?
                    """
            );
            selectStatement.setLong(1, userId);
            var resultSet = selectStatement.executeQuery();
            var permissions = new ArrayList<String>();
            while (resultSet.next()) {
                permissions.add(resultSet.getString("permissionCode"));
            }
            return permissions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(getPermissionByUserId(1L));
    }
}
