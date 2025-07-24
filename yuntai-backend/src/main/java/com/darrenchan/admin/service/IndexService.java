package com.darrenchan.admin.service;

import com.darrenchan.admin.bean.UserLoginInfo;

//用户登录获取用户信息时使用
public class IndexService {
    public static UserLoginInfo getUserInfo(String username) {
        var user = UserService.getUserByName(username);

        // 根据用户id获取权限列表
        if (user != null) {
            var permissionsByUserId = PermissionService.getPermissionByUserId(user.getId());
            var userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUsername(user.getUsername());
            userLoginInfo.getRoutes().addAll(permissionsByUserId);
            return userLoginInfo;
        }

        return null;
    }
}
