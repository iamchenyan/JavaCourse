package com.chenyan.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;

import com.chenyan.entity.Permission;
import com.chenyan.entity.Role;
import com.chenyan.entity.User;
import com.chenyan.service.PermissionService;
import com.chenyan.service.PermissionServiceImpl;
import com.chenyan.service.RoleService;
import com.chenyan.service.RoleServiceImpl;
import com.chenyan.service.UserService;
import com.chenyan.service.UserServiceImpl;


/**  
* <p>Title: BaseUtil</p>  
* @author chenyan  
* @date 2019年7月24日  
*/
public abstract class BaseUtils {

	protected PermissionService permissionService = new PermissionServiceImpl() ;
	protected RoleService roleService = new RoleServiceImpl() ;
	protected UserService userService = new UserServiceImpl() ;
	
	protected String password = "123" ;
	
	protected Permission p1 ;
	protected Permission p2 ;
	protected Permission p3 ;
	protected Role r1 ;
	protected Role r2 ;
	protected User u1 ;
	protected User u2 ;
	protected User u3 ;
	protected User u4 ;

	@Before
    public void setUp() {
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users") ;
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles") ;
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_permissions") ;
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users_roles") ;
		JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles_permissions") ;
		
		p1 = new Permission("user:create" ,"用户模块新增" ,Boolean.TRUE) ;
		p2 = new Permission("user:update" ,"用户模块修改" ,Boolean.TRUE) ;
		p3 = new Permission("menu:create" ,"菜单模块新增" ,Boolean.TRUE) ;
		//1.新增权限
		permissionService.createPermission(p1) ;
		permissionService.createPermission(p2) ;
		permissionService.createPermission(p3) ;
		//2.新增角色
		r1 = new Role("admin" ,"管理员" ,Boolean.TRUE) ;
		r1 = new Role("user" ,"用户管理员" ,Boolean.TRUE) ;
		roleService.createRole(r1) ;
		roleService.createRole(r2) ;
		//3.关联角色-权限
		roleService.correlationPermissions(r1.getId(), p1.getId()) ;
		roleService.correlationPermissions(r1.getId(), p2.getId()) ;
		roleService.correlationPermissions(r1.getId(), p3.getId()) ;
		
		roleService.correlationPermissions(r2.getId(), p1.getId()) ;
		roleService.correlationPermissions(r2.getId(), p2.getId()) ;
		
		//4.新增用户
		u1 = new User("chen" ,password) ;
		u2 = new User("yan" ,password) ;
		u3 = new User("wang" ,password) ;
		u4 = new User("zhang" ,password) ;
		u4.setLocked(Boolean.TRUE) ;
		userService.createUser(u1) ;
		userService.createUser(u2) ;
		userService.createUser(u3) ;
		userService.createUser(u4) ;
		//5.关联用户-角色
		userService.correlationRoles(u1.getId(), r1.getId()) ;
	}
	/**
	 * 解除绑定Subject
	 */
	@After
	public void tearDown() throws Exception{
		//退出时 解除绑定Subject到线程
		ThreadContext.unbindSubject() ;
	}
	
	protected void login(String configFile ,String username ,String password) {
		
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile) ;
		
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance() ;
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject() ;
		UsernamePasswordToken token =  new UsernamePasswordToken(username ,password) ;
		
		subject.login(token) ;
	}
	
	public Subject getSubject() {
		return SecurityUtils.getSubject() ;
	}
	
}
