package com.chenyan.springshiro.service;

import java.util.List;

import com.chenyan.springshiro.entity.Organization;

/**  
* <p>Title: OrganizationService</p>  
* @author chenyan  
* @date 2019年9月4日  
*/
public interface OrganizationService {

	public Organization createOrganization(Organization organization) ;
	public Organization updateOrganization(Organization organization) ;
	public void deleteOrganization(Long organizationId) ;
	
	Organization findOne(Long organizationId) ;
	List<Organization> findAll() ;
	
	Object findAllWithExclude(Organization excludeOrganization) ;

	void move(Organization source ,Organization target) ;
}
