package com.shirossm.mapper;

import com.shirossm.pojo.User;

/**
* <p>Title: UserMapper</p>  
* @author chenyan  
* @date 2019年9月18日
 */
public interface UserMapper {

    User login(String username);
}
