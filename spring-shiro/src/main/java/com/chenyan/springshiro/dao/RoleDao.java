package com.chenyan.springshiro.dao;

import java.util.List;

import com.chenyan.springshiro.entity.Role;

/**
 * <p>Title: RoleDao</p>
 *
 * @author chenyan
 * @date 2019年9月2日
 */
public interface RoleDao {

    public Role createRole(Role role);

    public Role updateRole(Role role);

    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);

    public List<Role> findAll();
}
