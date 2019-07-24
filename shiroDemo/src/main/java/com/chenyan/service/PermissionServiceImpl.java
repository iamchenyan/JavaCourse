package com.chenyan.service;

import com.chenyan.dao.PermissionDao;
import com.chenyan.dao.PermissionDaoImpl;
import com.chenyan.entity.Permission;

/**  
* <p>Title: PermissionServiceImpl</p>  
* @author chenyan  
* @date 2019年7月24日  
*/
public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao = new PermissionDaoImpl() ;
	
	@Override
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission) ;
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId) ;
	}

}
