package com.chenyan.entity;

import java.io.Serializable;

/**
 * role 权限关系
* <p>Title: RolePermission</p>  
* @author chenyan  
* @date 2019年7月23日
 */
public class RolePermission implements Serializable {
	
	private Long roleId ;
	private Long permissionId ;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	@Override
	public int hashCode() {
		int result = roleId != null ? roleId.hashCode() : 0 ;
		result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0) ;
		return result ;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true ;
		if(obj == null || getClass() != obj.getClass()) return false ;
		
		RolePermission that = (RolePermission) obj ;
		
		if(permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false ;
		
		return true ;
	}
	@Override
	public String toString() {
		return "RolePermssion{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
	}
	
}
