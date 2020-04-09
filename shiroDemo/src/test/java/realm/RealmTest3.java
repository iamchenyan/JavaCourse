package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class RealmTest3 implements Realm {

    @Override
    public String getName() {
        return "RealmTest3";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken; //仅支持UsernamePasswordToken类型的Token		return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal(); //得到用户
        String password = new String((char[]) token.getCredentials()); //得到密码
        if (!"chen".equals(username)) {
            throw new UnknownAccountException(); //用户名错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //密码错误
        }
        //验证成功返回 AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username + "@163.com", password, getName());
    }

}
