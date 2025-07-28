package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.ResultMessage;
import com.example.demo.common.enums.StatusEnum;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     */
    @PostMapping("/list")
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            for (User user : users) {
                // 处理状态
                StatusEnum status = StatusEnum.getByCode(user.getStatus());
                if (status != null) {
                    user.setStatusDesc(status.getDesc());
                } else {
                    user.setStatus(null);
                }
            }
            return Result.success(users, ResultMessage.SUCCESS);
        }
        return Result.success(new ArrayList<>(), ResultMessage.SUCCESS);
    }

    /**
     * 根据ID获取用户
     */
    @PostMapping("/getUserById")
    public Result<User> getUserById(@RequestBody User paramsUser) {
        System.out.println(paramsUser.getUserId());
        Integer userId = paramsUser.getUserId();
        if (userId == null) {
            return Result.validateFailed(ResultMessage.VALIDATE_FAILED);
        }
        User user = userService.getUserById(paramsUser.getUserId());
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.notFound();
        }
    }

    /**
     * 创建新用户
     */
    @PostMapping("/createUser")
    public Result<Integer> createUser(@RequestBody User user) {
        boolean success = userService.createUser(user);
        if (success) {
            return Result.success(user.getUserId(), ResultMessage.SUCCESS);
        } else {
            return Result.failed(ResultMessage.ERROR);
        }
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/updateUser")
    public Result<Void> updateUser(@RequestBody User user) {
        Integer userId = user.getUserId();
        if (userId == null) {
            return Result.validateFailed(ResultMessage.VALIDATE_FAILED);
        }
        boolean success = userService.updateUser(user);
        if (success) {
            return Result.success(null, ResultMessage.SUCCESS);
        } else {
            return Result.failed(ResultMessage.ERROR);
        }
    }

    /**
     * 删除用户
     */
    @PostMapping("/deleteUser")
    public Result<Void> deleteUser(@RequestBody Integer id) {
        if (id == null) {
            return Result.validateFailed(ResultMessage.VALIDATE_FAILED);
        }
        boolean success = userService.deleteUser(id);
        if (success) {
            return Result.success(null, ResultMessage.SUCCESS);
        } else {
            return Result.failed(ResultMessage.ERROR);
        }
    }
}

