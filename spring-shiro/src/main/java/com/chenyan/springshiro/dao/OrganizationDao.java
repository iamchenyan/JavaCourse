package com.chenyan.springshiro.dao;

import java.util.List;

import com.chenyan.springshiro.entity.Organization;

/**
 * <p>Title: OrganizationDao</p>
 *
 * @author chenyan
 * @date 2019年9月4日
 */
public interface OrganizationDao {

    public Organization createOrganization(Organization organization);

    public Organization updateOrganization(Organization organization);

    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);

    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
