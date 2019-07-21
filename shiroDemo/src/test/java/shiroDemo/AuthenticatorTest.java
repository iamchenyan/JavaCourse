package shiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

/**
* <p>Title: AuthenticatorTest</p>  
* @author chenyan  
* @date 2019年7月19日
 */
public class AuthenticatorTest {
	
	/**
	 * 	Authenticator 的职责是验证用户账号：
	 * 	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException { }
	 * 验证成功 返回 AuthenticationInfo 验证信息，此信息中包含了身份凭证；失败抛出AuthenticationException
	 * 
	 * SecurityManager接口继承了Authenticator ,另还有一个 ModularRealmAuthenticator实现，委托给多个Realm进行验证，验证规则通过 AuthenticationStrategy接口指定；
	 * 		public interface SecurityManager extends Authenticator, Authorizer, SessionManager { }
	 * 默认实现：
	 * 		FirstSuccessfulStrategy：有一个Realm验证成功即可，只返回第一个Realm身份验证成功的信息；
	 * 		AtLeastOneSuccessfulStrategy：有一个Realm验证成功即可，返回所有Realm身份验证成功的信息；
	 * 		AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败；
	 * 
	 * ModularRealmAuthenticator默认使用AtLeastOneSuccessfulStrategy策略。
	 */

	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-all-success.ini") ;
		
		Subject subject = SecurityUtils.getSubject() ;
		//得到一个身份集合，其包含了Realm验证成功的身份信息
		PrincipalCollection prinacipalCollection = subject.getPrincipals() ;
		Assert.assertEquals(2, prinacipalCollection.asList().size()) ;
	}
	
	@Test(expected=UnknownAccountException.class)
	public void testAllSuccessfulStrategyWithFail() {
		login("classpath:shiro-authenticator-all-fail.ini") ;
		Subject subject = SecurityUtils.getSubject() ;
		
		//AuthenticationStrategy
	}
	
	private static void login(String configFile) {
		
		//1.获取SecurityManager 工厂，使用 ini 配置文件初始化
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile) ;
		//2.得到 SecurityManager实例，绑定给 SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance() ;
		SecurityUtils.setSecurityManager(securityManager) ;
		//3.得到 Subject实例，创建 用户名/密码身份验证 Token(即用户身份/凭证)
		Subject subject = SecurityUtils.getSubject() ;
		UsernamePasswordToken token = new UsernamePasswordToken("chen" ,"123") ;
		
		subject.login(token) ;
	}
	
	public static void main(String[] args) {
		//testAllSuccessfulStrategyWithSuccess() ;
	}
	
}
