package com.example.demo.mapper;

import com.example.demo.common.Result;
import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
@Repository
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

    /**
     * 创建角色
     */
    Result<Boolean> createRole(@RequestBody Role role);
}
