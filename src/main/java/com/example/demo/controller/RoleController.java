package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.ResultMessage;
import com.example.demo.common.enums.StatusEnum;
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
            return Result.success(roleList, ResultMessage.SUCCESS);
        }
        return Result.success(new ArrayList<>(), ResultMessage.SUCCESS);
    }

    @PostMapping("/getRoleById")
    public Result<Role> getRoleById(@RequestBody Role role) {
        Integer id = role.getRoleId();
        if (id == null) {
            return Result.validateFailed(ResultMessage.VALIDATE_FAILED);
        }
        Role roleData = roleService.getRoleById(role.getRoleId());
        if (roleData != null) {
            return Result.success(roleData);
        } else {
            return Result.notFound();
        }
    }

    @PostMapping("/createRole")
    public Result<Role> createRole(@RequestBody Role role) {
        Integer result = roleService.createRole(role);
        if (result > 0) {
            return Result.success(null, ResultMessage.SUCCESS);
        }
        return Result.failed(ResultMessage.ERROR);
    }
}
