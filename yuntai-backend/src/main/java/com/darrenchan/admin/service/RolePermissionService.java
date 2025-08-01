package com.darrenchan.admin.service;

import com.darrenchan.admin.bean.Permission;
import com.darrenchan.admin.util.DataSourceProvider;
import com.darrenchan.admin.util.PermissionHelper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RolePermissionService {
    private static final DataSource ds = DataSourceProvider.getDataSource();
    /**
     * 根据角色ID获取角色对应的所有权限
     */
    public static List<Permission> getPermissionsByRoleId(Long roleId) {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            // 获取所有权限
            var selectRolePermissionStatement = connection.prepareStatement(
                    "SELECT permission_id FROM role_permission WHERE role_id = ?"
            );
            selectRolePermissionStatement.setLong(1, roleId);
            var resultSet = selectRolePermissionStatement.executeQuery();
            var allPermissions = new ArrayList<Long>();
            while (resultSet.next()) {
                allPermissions.add(resultSet.getLong("permission_id"));
            }

            var selectPermissionStatement = connection.prepareStatement(
                    "SELECT id, parent_id, permission_name, permission_code FROM permission"
            );
            resultSet = selectPermissionStatement.executeQuery();
            var permissions = new ArrayList<Permission>();
            while (resultSet.next()) {
                var permission = new Permission();
                permission.setId(resultSet.getLong("id"));
                permission.setParentId(resultSet.getLong("parent_id"));
                permission.setPermissionName(resultSet.getString("permission_name"));
                permission.setPermissionCode(resultSet.getString("permission_code"));
                if (allPermissions.contains(permission.getId())) {
                    permission.setSelect(true);
                }
                permissions.add(permission);
            }

            // 构建一个树形权限结构
            var permissionsList = PermissionHelper.build(permissions);

            selectRolePermissionStatement.close();
            selectPermissionStatement.close();
            connection.close();

            return permissionsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 添加角色和权限的关系
     */
    public static void addRolePermissions(Long roleId, List<Long> permissionIds) {
        try {
//            Class.forName(DATABASE.DRIVER);
//            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            Connection connection = ds.getConnection();
            var deleteStatement = connection.prepareStatement(
                    "DELETE FROM role_permission WHERE role_id = ?"
            );
            deleteStatement.setLong(1, roleId);
            deleteStatement.execute();

            var insertStatement = connection.prepareStatement(
                    "INSERT INTO role_permission (role_id, permission_id) VALUES (?, ?)"
            );

            for (var permissionId : permissionIds) {
                insertStatement.setLong(1, roleId);
                insertStatement.setLong(2, permissionId);
                insertStatement.execute();
            }

            deleteStatement.close();
            insertStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (Permission permission : getPermissionsByRoleId(1L)) {
            System.out.println(permission.getPermissionName() + " - " + permission.getSelect());
        }
    }
}
