package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     */
    @GetMapping
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }

    /**
     * 根据ID获取用户
     */
    @PostMapping("/getUserById")
    public Result<User> getUserById(@RequestBody Integer id) {
        System.out.println(id);
        User user = userService.getUserById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.notFound();
        }
    }

    /**
     * 创建新用户
     */
    @PostMapping
    public Result<Integer> createUser(@RequestBody User user) {
        boolean success = userService.createUser(user);
        if (success) {
            return Result.success(user.getId(), "用户创建成功");
        } else {
            return Result.failed("用户创建失败");
        }
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/updateUser")
    public Result<Void> updateUser(@RequestBody User user) {
        Integer userId = user.getId();
        if (userId == null) {
            return Result.failed("用户更新失败");
        }
        boolean success = userService.updateUser(user);
        if (success) {
            return Result.success(null, "用户更新成功");
        } else {
            return Result.failed("用户更新失败");
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/deleteUser")
    public Result<Void> deleteUser(@RequestBody Integer id) {
        if (id == null) {
            return Result.failed("用户删除失败");
        }
        boolean success = userService.deleteUser(id);
        if (success) {
            return Result.success(null, "用户删除成功");
        } else {
            return Result.failed("用户删除失败");
        }
    }
}

