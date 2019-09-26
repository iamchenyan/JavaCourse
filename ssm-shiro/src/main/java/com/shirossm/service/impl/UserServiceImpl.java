package com.shirossm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirossm.mapper.UserMapper;
import com.shirossm.pojo.Permission;
import com.shirossm.pojo.Role;
import com.shirossm.pojo.User;
import com.shirossm.service.PasswordHelper;
import com.shirossm.service.UserService;

/**
* <p>Title: UserServiceImpl</p>  
* @author chenyan  
* @date 2019年9月18日
 */
@Service
public class UserServiceImpl implements UserService {

    //注入
    @Autowired
    private UserMapper userMapper ;
    
    @Autowired
    private PasswordHelper passwordHelper ;

    /**
     * 用户登录的方法
     */
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    public List<User> findAll() {
        return userMapper.findAll() ;
    }

    public User findById(Long id) {
        return null;
    }

    /**
     * 创建用户
     * @param user
     */
    public void create(User user) {
    	//加密
    	passwordHelper.encryptPassword(user) ;
    	userMapper.create(user) ;
    }

    public void delete(Long id) {

    }
    
    /**
     * 修改用户
     * @param user
     */
    public void update(User user) {
    	//加密
    	passwordHelper.encryptPassword(user) ;
    	userMapper.update(user) ;
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return roles
     */
	@Override
	public List<Role> findRoles(String username) {
		return userMapper.findRoles(username) ;
	}

	/**
     * 根据用户名查找其他权限
     * @param username
     * @return
     */
	@Override
	public List<Permission> findPermissions(String username) {
		return userMapper.findPermissions(username) ;
	}
	
	/**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		userMapper.correlationRoles(userId, roleIds) ;
	}

	@Override
	public void deleteAllUserRoles(Long id) {
		userMapper.deleteAllUserRoles(id) ;
	}
	
	
}
