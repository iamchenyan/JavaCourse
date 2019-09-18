package com.shirossm.pojo;

import java.io.Serializable;

/**
* <p>Title: 用户登录</p>  
* @author chenyan  
* @date 2019年9月18日
 */
public class User implements Serializable {

    //用户id
    private Long id;
    //用户登录名
    private String username;
    //用户密码
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
