package com.chenyan.service;

import com.chenyan.entity.Permission;
/**
* <p>Title: PermissionService</p>  
* @author chenyan  
* @date 2019年7月23日
 */
public interface PermissionService {

	public Permission createPermission(Permission permission)  ;
	
	public void deletePermission(Long permissionId) ;
	
}
