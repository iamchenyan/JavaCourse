package com.shirossm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirossm.mapper.UserMapper;
import com.shirossm.pojo.User;
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
    private UserMapper userMapper;

    /**
     * 用户登录的方法
     */
    public User login(String username) {
        return userMapper.login(username);
    }

    public List<User> findAll() {
        return null;
    }

    public User findById(Long id) {
        return null;
    }

    public void create(User user) {

    }

    public void delete(Long id) {

    }

    public void update(User user) {

    }
}
