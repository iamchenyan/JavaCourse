import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shirossm.realm.UserRealm;

/**  
* <p>Title:定位 ShiroFilterNotFound </p>  
* @author chenyan  
* @date 2019年9月22日  
*/
public class ShiroFilterNotFound {
	
	/**
	 * ClassPathXmlApplicationContext：是ApplicationContext的一个实现类
	 */
	private ApplicationContext ac = null ;
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-shiro.xml");
	}
	
	@Test
	public void testShiro() {
//      DefaultWebSecurityManager securityManager = ac.getBean("securityManager", DefaultWebSecurityManager.class);
//      System.out.println(securityManager);
//      LifecycleBeanPostProcessor lifecycleBeanPostProcessor = ac.getBean("lifecycleBeanPostProcessor", LifecycleBeanPostProcessor.class);
//      System.out.println("lifecycleBeanPostProcessor = " + lifecycleBeanPostProcessor);
      UserRealm shiroRealm = ac.getBean("shiroRealm", UserRealm.class);
      System.out.println("shiroRealm = " + shiroRealm);
//      ShiroFilterFactoryBean shiroFilter = ac.getBean("shiroFilter", ShiroFilterFactoryBean.class);
//      System.out.println("shiroFilter = " + shiroFilter);
      System.out.println("ac = " + ac);
  }
	
}
