package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    @Transactional
    public boolean createUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }

    @Override
    @Transactional
    public boolean deleteUser(Integer id) {
        return userMapper.deleteById(id) > 0;
    }
}

