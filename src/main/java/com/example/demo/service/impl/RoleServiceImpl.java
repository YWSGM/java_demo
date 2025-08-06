package com.example.demo.service.impl;

import com.example.demo.common.Result;
import com.example.demo.common.ResultMessage;
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
    public Integer deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public Result<Role> updateRole(Role role) {
        Integer roleId = role.getRoleId();

        String roleName = role.getRoleName();

        if (roleId == null) {
            return Result.failed("角色ID不能为空");
        }

        // 排除本身
        Role roleWithSameName = findByRoleName(roleName);
        if (roleWithSameName != null && !roleWithSameName.getRoleId().equals(roleId)) {
            return Result.failed("角色名已存在");
        }

        Integer result = roleMapper.updateRole(role);
        if (result > 0) {
            return Result.success();
        }
        return Result.failed();
    }
}
