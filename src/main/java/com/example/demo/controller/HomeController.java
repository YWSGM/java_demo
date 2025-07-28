package com.example.demo.controller;

import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Result<Map<String, Object>> home() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Spring Boot MyBatis Demo API");
        data.put("endpoints", new String[]{
                "GET /api/users - 获取所有用户",
                "POST /api/users/{id} - 根据ID获取用户",
                "POST /api/users/username/{username} - 根据用户名获取用户",
                "POST /api/role/getRoleById - 根据ID获取角色（需要POST请求，请求体中包含id字段）"
        });
        return Result.success(data);
    }
}

