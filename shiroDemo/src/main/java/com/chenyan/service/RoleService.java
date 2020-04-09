package com.chenyan.service;

import com.chenyan.entity.Role;

/**
 * <p>Title: RoleService</p>
 *
 * @author chenyan
 * @date 2019年7月23日
 */
public interface RoleService {

    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    //添加角色权限之间的关系
    public void correlationPermissions(Long roleId, Long... permissionIds);

    //移除角色权限之间的关系
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
