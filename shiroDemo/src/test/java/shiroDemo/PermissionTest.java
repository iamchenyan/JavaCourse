package shiroDemo;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Test;

import junit.framework.Assert;

/**
* 基于权限的访问控制 
* <p>Title: PermissionTest</p>  
* @author chenyan  
* @date 2019年7月22日
 */
public class PermissionTest extends BaseTest {
	
	@Test
	public void testPermistted() {
		login("classpath:shiro-permission.ini" ,"chen" ,"123") ;
		//判断拥有权限（user:create）
		Assert.assertTrue(subject().isPermitted("user:create")) ;
		//
		Assert.assertTrue(subject().isPermittedAll("user:create" ,"user:delete")) ;
		
		Assert.assertFalse(subject().isPermitted("user:view")) ;
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testCheckPermission() {
		login("classpath:shiro-permission.ini" ,"yan" ,"123") ;
		
		subject().checkPermission("user:create") ;
		subject().checkPermissions("user:delete" ,"user:update") ;
		
		subject().checkPermission("user:view") ;
	}

}
