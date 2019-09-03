package com.chenyan.springshiro.dao;

import java.util.List;

import com.chenyan.springshiro.entity.User;

/**  
* <p>Title: UserDao</p>  
* @author chenyan  
* @date 2019年9月2日  
*/
public interface UserDao {

	public User createUser(User user) ;
	
	public User updateUser(User user) ;
	
	public void deleteUser(Long userId) ;
	
	User findOne(Long userId) ;
	
	List<User> findAll() ;
	
	User findByUsername(String username) ;
}
