package com.chenyan.springshiro.dao;

import java.util.List;

import com.chenyan.springshiro.entity.Resource;

/**  
* <p>Title: ResourceDao</p>  
* @author chenyan  
* @date 2019年9月2日  
*/
public interface ResourceDao {

	public Resource createResource(Resource resource) ;
	public Resource updateResource(Resource resource) ;
	public void deleteResourcr(Long resourceId) ;
	
	Resource findOne(Long resourceId) ;
	List<Resource> findAll() ;
}
