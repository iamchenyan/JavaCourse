package com.cms.cycms.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cms.cycms.service.api.CmsUserService;

public class UsernamePasswordCaptchaRealm extends AuthorizingRealm {

    @Autowired
    private CmsUserService cmsUserService;

    /**
     * 登录
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 权限
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.在 token中获取用户名
        String username = (String) token.getPrincipal();


        return null;
    }


}
