package com.chenyan.service;

import java.util.Set;

import com.chenyan.entity.User;

/**
 * <p>Title: UserService</p>
 *
 * @author chenyan
 * @date 2019年7月23日
 */
public interface UserService {
    //创建用户
    public User createUser(User user);

    //修改密码
    public void changePassword(Long userId, String newPassword);

    //添加用户-角色关系
    public void correlationRoles(Long userId, Long... roleIds);

    //移除用户-角色关系
    public void uncorrelationRoles(Long userId, Long... roleIds);

    //根据用户名查找用户
    public User findByUsername(String username);

    //根据用户名查找角色
    public Set<String> findRoles(String username);

    //根据用户名查找权限
    public Set<String> findPermissions(String username);
}
