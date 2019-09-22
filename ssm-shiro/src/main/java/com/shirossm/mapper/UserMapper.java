package com.shirossm.mapper;

import java.util.List;

import com.shirossm.pojo.Permission;
import com.shirossm.pojo.Role;
import com.shirossm.pojo.User;

/**
* <p>Title: UserMapper</p>  
* @author chenyan  
* @date 2019年9月18日
 */
public interface UserMapper {

    User findByName(String username) ;
    
    void create(User user) ;
    
    void update(User user) ;
    
    List<Role> findRoles(String username) ;
    
    List<Permission> findPermissions(String username) ;
}
