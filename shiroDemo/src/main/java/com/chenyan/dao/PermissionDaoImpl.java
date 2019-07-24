/**
 * <p>Title: PermissionDaoImpl.java</p>
 * @author chenyan
 * @date 2019年7月24日
 */
package com.chenyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.chenyan.entity.Permission;
import com.chenyan.utils.JdbcTemplateUtils;

/**  
* <p>Title: PermissionDaoImpl</p>  
* @author chenyan  
* @date 2019年7月24日  
*/
public class PermissionDaoImpl implements PermissionDao{

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate() ;
	
	@Override
	public Permission createPermission(Permission permission) {
		final String sql = "insert into sys_permissions(permission ,description ,available) values(? ,? ,?)" ;
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder() ;
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psst = con.prepareStatement(sql ,new String[] {"id"}) ;
				psst.setString(1, permission.getPermission()) ;
				psst.setString(2, permission.getDescription()) ;
				psst.setBoolean(3, permission.getAvailable()) ;
				return psst ;
			}
		} ,keyHolder) ;
		permission.setId(keyHolder.getKey().longValue()) ;
		return permission ;
	}

	@Override
	public void deletePermission(Long permissionId) {
		//先把与permission 关联的相关表数据删除
		String sql = "delete from sys_roles_permissions where permission_id = ?" ;
		jdbcTemplate.update(sql ,permissionId) ;
		
		sql = "delete from sys_permissions where id = ?" ;
		jdbcTemplate.update(sql ,permissionId) ;
	}

}
