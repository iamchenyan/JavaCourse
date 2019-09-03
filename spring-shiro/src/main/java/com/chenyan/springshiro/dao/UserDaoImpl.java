package com.chenyan.springshiro.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chenyan.springshiro.entity.User;

/**  
* <p>Title: UserDaoImpl</p>  
* @author chenyan  
* @date 2019年9月2日  
*/
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findOne(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUsername(String username) {
		String sql = "select id ,organization_id ,username ,password ,salt ,role_ids as roleIdsStr ,locked from sys_user where username = ? " ;
		List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class) ,username) ;
		if(userList.size() == 0) {
			return null ;
		}
		return userList.get(0) ;
	}

}
