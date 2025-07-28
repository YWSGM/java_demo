package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User findById(@Param("id") Integer id);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> findAll();

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(@Param("username") String username);

    /**
     * 插入新用户
     * @param user 用户对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 删除用户
     * @param id 用户ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);
}

