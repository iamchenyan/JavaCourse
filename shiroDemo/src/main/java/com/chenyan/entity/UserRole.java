package com.chenyan.entity;

import java.io.Serializable;

/**
 * user role关系
 * <p>Title: UserRole</p>
 *
 * @author chenyan
 * @date 2019年7月23日
 */
public class UserRole implements Serializable {

    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserRole userRole = (UserRole) obj;

        if (roleId != null ? roleId.equals(userRole.roleId) : userRole.roleId != null) return false;
        if (userId != null ? userId.equals(userRole.userId) : userRole.userId != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }

}
