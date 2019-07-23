package com.chenyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.chenyan.entity.Permission;
import com.chenyan.entity.Role;
import com.chenyan.utils.JdbcTemplateUtils;

/**  
* <p>Title: RoleDaoImpl</p>  
* @author chenyan  
* @date 2019年7月23日  
*/
public class RoleDaoImpl implements RoleDao {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate() ;
	
	@Override
	public Role createRole(final Role role) {
		final String sql = "insert into sys_roles(role ,description ,available) value(? ,? ,?)" ;
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder() ;
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psst = con.prepareStatement(sql ,new String[] {"id"}) ;
				psst.setString(1, role.getRole()) ;
				psst.setString(2, role.getDescription()) ;
				psst.setBoolean(3, role.getAvailable()) ;
				return psst ;
			}
		} ,keyHolder) ;
		role.setId(keyHolder.getKey().longValue()) ;
		return role ;
	}

	@Override
	public void deleteRole(Long roleId) {
		//先把和 role关联的1相关表数据删除
		String sql = "delete from sys_users_roles where role_id = ?" ;
		jdbcTemplate.update(sql ,roleId) ;
		
		sql = "delete from sys_roles where id = ?" ;
		jdbcTemplate.update(sql, roleId) ;
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if(permissionIds == null || permissionIds.length == 0) {
			return ;
		}
		String sql = "insert into sys_roles_permissions(role_id ,permission_id) values(? ,?)" ;
		for(Long permissionId : permissionIds) {
			if(!exists(roleId ,permissionId)) {
				jdbcTemplate.update(sql ,roleId ,permissionId) ;
			}
		}
	}
	
	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		if(permissionIds == null || permissionIds.length == 0) {
			return ;
		}
		String sql = "delete from sys_roles_permissions where role_id = ? and permission_id = ?" ;
		for(Long permissionId : permissionIds) {
            if(exists(roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
	}

	/**
	 * @param roleId
	 * @param permissionId
	 * @return
	 */
	private boolean exists(Long roleId, Long permissionId) {
		String sql = "select count(1) from sys_roles_permissions where role_id = ? and permission_id = ?" ;
		return jdbcTemplate.queryForObject(sql, Integer.class, roleId, permissionId) != 0 ;
	}



}
