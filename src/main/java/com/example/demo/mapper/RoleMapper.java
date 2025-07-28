package com.example.demo.mapper;

import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    /**
     * 根据id查询角色信息
     * @param id 角色
     * @return 角色对象
     */
    Role findByRoleId(@Param("id") Integer id);

    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<Role> getRoleList();
}
