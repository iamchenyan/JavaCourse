package com.chenyan.springshiro.service;

import java.util.List;
import java.util.Set;

import com.chenyan.springshiro.entity.Resource;

/**  
* <p>Title: ResourceService</p>  
* @author chenyan  
* @date 2019年9月2日  
*/
public interface ResourceService {

	public Resource createResource(Resource resource) ;
	public Resource updateResource(Resource resource) ;
	public void deleteResource(Long resourceId) ;
	
	Resource findOne(Long resourceId) ;
	List<Resource> findAll() ;

	/**
	 * 得到资源对应的权限字符串
	 * @param resource
	 * @return
	 */
	Set<String> findPermissions(Set<Long> resourceIds) ;
	
	/**
	 * 根据用户权限得到菜单
	 * @param permissions
	 * @return
	 */
	List<Resource> findMenus(Set<String> permissions) ;
	
}
