package com.chenyan.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.chenyan.entity.User;
import com.chenyan.service.UserService;
import com.chenyan.service.UserServiceImpl;

/**
 * <p>Title: UserRealm</p>
 *
 * @author chenyan
 * @date 2019年7月26日
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 1. 父类AuthorizingRealm 将获取 Subject分为两步：获取身份验证信息（doGetAuthenticationInfo）、授权信息（doGetAuthorizationInfo）
     * 2. doGetAuthenticationInfo 获取身份验证和相关数据：首先根据传入的用户名获取 User信息，如果User为空，
     * 抛出没有找到账号异常(UnknownAccountException)，如果找到了但锁定了抛出锁定异常(LockedAccountException)；最后生成 AuthenticationInfo；
     * 交给间接父类 AuthenticatingRealm 使用 CredentialsMatcher进行判断密码是否匹配，如果不匹配将抛出密码错误异常 IncorrectCredentialsException；
     * 在组装 SimpleAuthenticationInfo需要传入身份信息(用户名)、凭证(密文密码)、盐(username+salt)，CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
     * 3. doGetAuthorizationInfo 获取授权信息；PrincipalCollection 是身份集合，因为现在就一个Realm，所以直接调用 getPrimaryPrincipal() 得到之前传入的用户名
     * 然后根据用户名调用 UserService接口获取角色权限信息，组装 AuthorizationInfo。
     */

    //类注入
    private UserService userService = new UserServiceImpl();

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();//没找到账号
        }
        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException();//账号锁定O
        }
        //交给AuthenticatingRealm 使用 CredentialsMatche进行密码匹配，可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName());//realm name

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));

        return authorizationInfo;
    }

}
