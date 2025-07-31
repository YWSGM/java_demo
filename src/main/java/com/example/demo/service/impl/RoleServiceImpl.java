package com.example.demo.service.impl;

import com.example.demo.common.Result;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.findByRoleId(id);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public Result<Boolean> createRole(Role role) {
        return roleMapper.createRole(role);
    }
}
