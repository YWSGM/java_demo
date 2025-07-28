package com.example.demo.mapper;

import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    /**
     * 根据id查询角色信息
     * @param id 角色
     * @return 角色对象
     */
    Role findByRoleId(@Param("id") Integer id);
}
