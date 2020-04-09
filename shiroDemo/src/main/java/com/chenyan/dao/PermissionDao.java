package com.chenyan.dao;

import com.chenyan.entity.Permission;

/**
 * <p>Title: PermissionDao</p>
 *
 * @author chenyan
 * @date 2019年7月24日
 */
public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}
