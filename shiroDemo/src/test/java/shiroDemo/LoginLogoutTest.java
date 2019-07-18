package shiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;

/**
* <p>Title: LoginLogoutTest</p>  
* @author chenyan  
* @date 2019年7月18日
 */
public class LoginLogoutTest {
	
	public void testHelloShiro() {
		/**
		 * 1.首先通过 new IniSecurityManagerFactory(String iniResourcePath) ,指定ini配置文件用来创建 SecurityManager 工厂
		 * 2.获取SecurityManager 并绑定到 SecurityUtils ,全局设置，设置一次即可
		 * 3.通过SecurityManager 得到 Subject ,会自动绑定到当前线程，在web环境请求结束时需要解除绑定；然后获取身份验证的 Token (如 用户名/密码)
		 * 4.调用subject.login() ;进行登录，会自动委托给 SecurityManager.login() ;进行登录；
		 * 5.如果身份验证失败请捕获 Authentication 或子类，常见如：DisabledAccountException(禁用的账户)，LockedAccountException(锁定的账户)，
		 *   UnknownAccountException(错误的账户)，ExcessiveAttemptsException(登录失败次数过多)，IncorrectCredentialsException(错误的凭证)，
		 *   ExpiredCredentialsException(过期的凭证)等；对于页面的错误消息提示最好使用"用户名/密码错误" 而不是"用户名错误/密码错误"，防止一些恶意扫描
		 * 6.最后调用 subject.logout() ; 退出，会自动委托给 SecurityManager.logout() ;
		 */
		/**
		 * 1.收集用户身份/凭证，即如用户名/密码 ；
		 * 2.调用Subject.login() ;进行登录，如果失败将得到相应的 AuthenticationException 异常，根据异常提示用户错误信息；
		 * 3.调用Subject.logout() ;退出操作；
		 */
		/**
		 * 流程：
		 * 1.调用Subject.login(Token) ;进行登录，自动委托给SecurityManager，调用之前必须通过 SecurityUtils.setSecurityManager() ;设置；
		 * 2.SecurityManager 负责真正的身份验证逻辑；她会委托给 Authentication 进行验证；
		 * 3.Authentication 是真正的身份验证者，Shiro API中核心的身份认证入口，可以自定义插入自己的实现。
		 * 4.Authentication 可能会委托给相应的 AuthenticationStrategy 进行多 Realm 身份验证，默认 ModularRealmAuthentication
		 *   会调用AuthenticationStrategy进行多Realm身份验证。
		 * 5.Authentication 会把相应的 token传入Realm，从Realm获取身份验证信息，如果没有返回/抛出异常表示身份验证失败。此处可以配置多个Realm，将按照相应的顺序及策略访问。
		 */
		
		/**
		 * Realm：
		 * 		shiro从realm(域)中获取安全数据(如用户，角色，权限)，就是说 SecurityManager要验证用户身份，那么需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到
		 * 	相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。如之前的 ini配置方式将使用 org.apache.shiro.realm.text.IniRealm.
		 * 		接口：
		 * 		String getName(); //返回一个唯一的 Realm名字
		 *  	Boolean supports(AuthenticationToken token); //判断此Realm是否支持此 Token
		 *  	AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException; //根据Token 获取认证信息
		 */
		
		//1.通过ini配置文件(shiro.ini) ,获得SecurityManager工厂(factory) 
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini") ;
		//2.得到SecurityManager实例 (securityManager) ,并绑定给 SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance() ;
		SecurityUtils.setSecurityManager(securityManager) ;
		//3.得到 Subject 及创建用户名/密码身份验证 Token(用户身份/凭证)
		Subject subject = SecurityUtils.getSubject() ;
		UsernamePasswordToken token = new UsernamePasswordToken("chen" ,"123") ;
		
		try {
			//4.登录（身份验证）
			subject.login(token) ;
		} catch (AuthenticationException e) {
			//身份验证失败
		}
		
		Assert.assertEquals(true, subject.isAuthenticated()) ; //断言用户已经登录
		
		//5.退出
		subject.logout() ;
	}
	
	public void testSingleRealm() {
		//1.通过ini配置文件(shiro-realm.ini) ,获得SecurityManager工厂(factory) 
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini") ;
		//2.得到SecurityManager实例 (securityManager) ,并绑定给 SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance() ;
		SecurityUtils.setSecurityManager(securityManager) ;
		//3.得到 Subject 及创建用户名/密码身份验证 Token(用户身份/凭证)
		Subject subject = SecurityUtils.getSubject() ;
		UsernamePasswordToken token = new UsernamePasswordToken("chen" ,"123") ;
		
		try {
			//4.登录（身份验证）
			subject.login(token) ;
		} catch (AuthenticationException e) {
			//身份验证失败
		}
		
		Assert.assertEquals(true, subject.isAuthenticated()) ; //断言用户已经登录
		
		//5.退出
		subject.logout() ;
	}
	
	

}
