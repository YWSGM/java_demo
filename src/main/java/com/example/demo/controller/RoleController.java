package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    public Result<List<Role>> getRoleList() {
        List<Role> roleList = roleService.getRoleList();
        if (!roleList.isEmpty()) {
            return Result.success(roleList, "操作成功");
        }
        return Result.success(new ArrayList<>(), "操作成功");
    }

    @PostMapping("/getRoleById")
    public Result<Role> getRoleById(@RequestBody Role role) {
        Role roleData = roleService.getRoleById(role.getId());
        if (roleData != null) {
            return Result.success(roleData);
        } else {
            return Result.notFound();
        }
    }
}
