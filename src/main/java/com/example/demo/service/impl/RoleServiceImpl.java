package com.example.demo.service.impl;

import com.example.demo.common.Result;
import com.example.demo.common.enums.StatusEnum;
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
        List<Role> roleList = roleMapper.getRoleList();
        roleList.forEach(role -> {
            // 处理状态
            StatusEnum status = StatusEnum.getByCode(role.getStatus());
            if (status != null) {
                role.setStatusDesc(status.getDesc());
            } else {
                role.setStatusDesc(null);
            }
        });
        return roleList;
    }

    @Override
    public Integer createRole(Role role) {
        String roleName = role.getRoleName();

        Role roleInfo = findByRoleName(roleName);

        if (roleInfo != null) {
            return 0;
        }

        return roleMapper.createRole(role);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleMapper.findByRoleName(roleName);
    }

    @Override
    public Role findByRoleName(Role role) {
        return null;
    }
}
