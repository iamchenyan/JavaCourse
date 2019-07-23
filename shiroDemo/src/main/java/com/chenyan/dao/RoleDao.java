package com.chenyan.dao;

import com.chenyan.entity.Role;

/**  
* <p>Title: RoleDao</p>  
* @author chenyan  
* @date 2019年7月23日  
*/
public interface RoleDao {

	public Role createRole(Role role) ;
	public void deleteRole(Long roleId) ;
	
	public void correlationPermissions(Long roleId ,Long... permissionIds) ;
	public void uncorrelationPermissions(Long roleId ,Long... permissionIds) ;
}
