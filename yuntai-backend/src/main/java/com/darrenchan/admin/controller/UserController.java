package com.darrenchan.admin.controller;

import com.darrenchan.admin.bean.User;
import com.darrenchan.admin.bean.UserRole;
import com.darrenchan.admin.service.UserRoleService;
import com.darrenchan.admin.service.UserService;
import com.darrenchan.admin.util.MD5;
import com.darrenchan.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    /**
     * 获取所有用户
     */
    @GetMapping("getAllUsers") //127.0.0.1:8080/admin/user/getAllUsers
    public Result<List<User>> getAllUsers() {
        return Result.of(200, "success", UserService.getAllUsers());
    }

    /**
     * 根据用户ID获取用户信息
     */
    @GetMapping("getUser/{userId}") //127.0.0.1:8080/admin/user/getUser/3
    public Result<User> getUserById(@PathVariable Long userId) {
        return Result.of(200, "success", UserService.getUserById(userId));
    }

    /**
     * 新增用户
     */
    @PostMapping("addUser")
    public Result<String> addUser(@RequestBody User userInfo) {
        UserService.addUser(
                userInfo.getUsername(),
                MD5.encrypt(userInfo.getPassword())
        );
        return Result.of(200, "success", "新添加了一个用户");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("deleteUser/{userId}")
    public Result<String> deleteUser(@PathVariable Long userId) {
        UserService.deleteUserById(userId);
        return Result.of(200, "success", "删除用户完毕");
    }

    /**
     * 根据用户ID获取角色ID
     */
    @GetMapping("getRoleIdByUserId/{userId}")
    public Result<Long> getRoleIdByUserId(@PathVariable Long userId) {
        return Result.of(200, "success", UserRoleService.getRoleIdByUserId(userId));
    }

    /**
     * 为用户分配角色
     */
    @PostMapping("assignRoleToUser")
    public Result<String> assignRoleToUser(@RequestBody UserRole userRole) {
        UserRoleService.addUserIdRoleIdRelationship(userRole.getUserId(), userRole.getRoleId());
        return Result.of(200, "success", "分配角色成功");
    }
}
