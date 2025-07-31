package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User getUserById(Integer id);

    User findByUsername(String userName);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 创建新用户
     * @param user 用户对象
     * @return 是否成功
     */
    boolean createUser(User user);

    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 是否成功
     */
    boolean updateUser(User user);

    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否成功
     */
    boolean deleteUser(Integer id);
}

