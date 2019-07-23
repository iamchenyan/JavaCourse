package com.chenyan.entity;

import java.io.Serializable;

/**
* <p>Title: Permission</p>  
* @author chenyan  
* @date 2019年7月23日
 */
public class Permission implements Serializable {

	private Long id ;
	private String permission ;// 权限标识，如："user:create"
	private String description ;//描述，界面使用
	private Boolean available = Boolean.FALSE ;//是否可用，如果不可用将不会添加给用户
	
	public Permission() { }

	public Permission(Long id, String permission, String description, Boolean available) {
		this.permission = permission;
		this.description = description;
		this.available = available;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0 ;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true ;
		if(obj == null || getClass() != obj.getClass()) return false ;
		
		Permission role = (Permission) obj ;
		
		if(id != null ? !id.equals(role.id):role.id != null) return false ;
		
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Role{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
	}
	
	
}
