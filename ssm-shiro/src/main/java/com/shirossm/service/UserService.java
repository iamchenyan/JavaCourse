package com.shirossm.service;

import java.util.List;

import com.shirossm.pojo.Permission;
import com.shirossm.pojo.Role;
import com.shirossm.pojo.User;

/**
* <p>Title: UserService</p>  
* @author chenyan  
* @date 2019年9月18日
 */
public interface UserService extends BaseService<User> {

	/**
	 * 根据用户名查找其他角色
	 * @param username
	 * @return
	 */
	List<Role> findRoles(String username) ;
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
    User findByName(String username) ;
    
    /**
     * 根据用户名查找其他权限
     * @param username
     * @return
     */
    List<Permission> findPermissions(String username) ;
    
    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId ,Long... roleIds) ;
    
    /**
     * 删除此用户关联的所有角色信息
     * @param id
     */
    void deleteAllUserRoles(Long id) ;
}
