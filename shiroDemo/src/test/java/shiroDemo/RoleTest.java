package shiroDemo;

import java.util.Arrays;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
* <p>Title: RoleTest</p>  
* @author chenyan  
* @date 2019年7月19日
 */
public class RoleTest extends BaseTest {

	/**
	 * Shiro 不负责维护用户-角色信息，需要应用提供，只提供相应的接口方便验证
	 */
	
	@Test
	public void testHasRole() {
		
		login("classpath:shiro-role.ini" ,"chen" ,"123") ;
		//判断用户是否有角色：role1
		Assert.assertTrue(subject().hasRole("role1"));
		//判断拥有 role1 和 role2
		Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1" ,"role2")));
		
		//判断拥有 role1 和 role2 !role3
		boolean[] result = subject().hasRoles(Arrays.asList("role1" ,"role2" ,"role3")) ;
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testCheckRole() {
		login("classpath:shiro-role.ini" ,"chen" ,"123") ;
		//断言拥有角色：role1
		subject().checkRole("role1");
		//role1 and role3 失败抛出异常
		subject().checkRoles("role1" ,"role3");
	}
}
