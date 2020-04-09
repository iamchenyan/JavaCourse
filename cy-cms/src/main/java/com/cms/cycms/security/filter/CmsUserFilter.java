package com.cms.cycms.security.filter;

import org.apache.shiro.web.filter.authc.UserFilter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsUserFilter extends UserFilter {
    private String adminLoginUrl;
    private String adminPrefix;
}
