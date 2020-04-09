package shiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * <p>Title: BaseTest</p>
 *
 * @author chenyan
 * @date 2019年7月19日
 */
public abstract class BaseTest {

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时解除绑定Subject到线程，否则下次测试会有影响
    }

    protected void login(String configFile, String username, String password) {
        //1.获取 SecurityManager工厂，用 ini配置文件来初始化
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        //2.得到SecurityManager实例，并绑定给 SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.d得到Subject实例，用用户名/密码创建Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }


}
