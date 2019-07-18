package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
* <p>Title: RealmTest1</p>  
* @author chenyan  
* @date 2019年7月18日
 */
public class RealmTest1 implements Realm {

	@Override
	public String getName() {
		return "RealmTest1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持 UsernamePasswordToken类型的 Token
		return token instanceof UsernamePasswordToken ;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal() ; //得到用户名
		String password = new String((char[])token.getCredentials()) ; //密码
		if("chen".equals(username)) {
			throw new UnknownAccountException() ;
		}
		if("123".equals(password)) {
			throw new IncorrectCredentialsException() ;
		}
		// 身份验证成功返回一个 AuthenticationInfo实现
		return new SimpleAuthenticationInfo(username ,password ,getName()) ;
	}

}
