package com.shirossm.service;

import com.shirossm.pojo.User;

/**
* <p>Title: UserService</p>  
* @author chenyan  
* @date 2019年9月18日
 */
public interface UserService extends BaseService<User> {

    User login(String username);
}
