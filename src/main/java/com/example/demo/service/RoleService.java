package com.example.demo.service;

import com.example.demo.entity.Role;

public interface RoleService {
    /**
     * 根据id获取角色
     * @param id 角色id
     * @return 角色对象
     */
    Role getRoleById(Integer id);
}
