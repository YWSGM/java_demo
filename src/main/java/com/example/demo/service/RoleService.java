package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * 根据id获取角色
     * @param id 角色id
     * @return 角色对象
     */
    Role getRoleById(Integer id);

    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<Role> getRoleList();

    /**
     * 创建角色
     * @param role 角色
     * @return
     */
    Integer createRole(Role role);

    /**
     * 根据名称查询角色
     * @param roleName 角色名称
     * @return 角色
     */
    Role findByRoleName(String roleName);

    /**
     * 删除角色
     * @param id 角色id
     * @return
     */
    Integer deleteRole(Integer id);

    Result<Role> updateRole(Role role);
}
