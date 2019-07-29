package com.chenyan.service;

import com.chenyan.dao.RoleDao;
import com.chenyan.dao.RoleDaoImpl;
import com.chenyan.entity.Role;

/**  
* <p>Title: RoleServiceImpl</p>  
* @author chenyan  
* @date 2019年7月23日  
*/
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao = new RoleDaoImpl() ;
	
	@Override
	public Role createRole(Role role) {
		return roleDao.createRole(role) ;
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId) ;
	}

	/**
	 * 添加角色-权限关系
	 * @param roleId
	 * @param permissionIds
	 */
	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds) ;
	}
	
	/**
	 * 移除角色-权限关系
	 * @param roleId
	 * @param permissionIds
	 */
	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds) ;
	}

}
