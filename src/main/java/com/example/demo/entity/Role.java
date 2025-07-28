package com.example.demo.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private int id;
    private String roleName;
    private int userId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
