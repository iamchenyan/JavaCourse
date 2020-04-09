package com.shirossm.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.shirossm.pojo.Permission;
import com.shirossm.pojo.Role;
import com.shirossm.pojo.User;
import com.shirossm.service.UserService;

/**
 * <p>Title: UserRealm</p>
 *
 * @author chenyan
 * @date 2019年9月19日
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 权限验证
     *
     * @param principals 身份，主体的唯一标识，比如用户名、邮箱等，如果你将用户名和密码传给了Token对象，那么在Token对象中就能getPrincipal获取这个标识
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限验证--执行了doGetAuthorizationInfo...");

        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set<String> role = new HashSet<String>();
        List<Role> roles = userService.findRoles(username);
        for (Role r : roles) {
            role.add(r.getRole());
        }
        authorizationInfo.setRoles(role);

        Set<String> permission = new HashSet<String>();
        List<Permission> permissions = userService.findPermissions(username);
        for (Permission p : permissions) {
            permission.add(p.getPermission());
        }

        authorizationInfo.setStringPermissions(permission);
        return authorizationInfo;
    }

    /**
     * 身份验证
     *
     * @param token
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("身份校验--执行了goGetAuthenticationInfo...");
        String username = (String) token.getPrincipal();
        User user = userService.findByName(username);
        if (user == null) {
            throw new UnknownAccountException(); //没有找到账号
        }
        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //账号锁定
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()), //salt=username+salt
                getName() //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
