package shiroDemo;

import org.junit.Test;

import com.chenyan.utils.BaseUtils;

import junit.framework.Assert;

/**  
* <p>Title: UserRealmTest</p>  
* @author chenyan  
* @date 2019年7月29日  
*/
public class UserRealmTest extends BaseUtils{

	@Test
	public void testLonginSuccess() {
		login("classpath:shiro-salt.ini" ,u1.getUsername() ,password) ;
		Assert.assertTrue(getSubject().isAuthenticated()) ;
	}
	
}
