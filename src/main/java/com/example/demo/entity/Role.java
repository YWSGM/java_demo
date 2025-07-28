package com.example.demo.entity;

import com.example.demo.common.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private int roleId;
    private String roleName;
    private String roleDesc;
    private int status;
    private String createTime;
    private String updateTime;
    private String statusDesc;

    public Integer getRoleId() { return roleId; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getRoleDesc() { return roleDesc; }
    public void setRoleDesc(String roleDesc) { this.roleDesc = roleDesc; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }

    public String getUpdateTime() { return updateTime; }
    public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }

    public String getStatusDesc() { return statusDesc; }
    public void setStatusDesc(String statusDesc) { this.statusDesc = statusDesc; }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", statusDesc=" + statusDesc +
                '}';
    }
}
