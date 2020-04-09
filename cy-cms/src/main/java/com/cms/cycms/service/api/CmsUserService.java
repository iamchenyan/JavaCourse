package com.cms.cycms.service.api;

import com.cms.cycms.entity.CmsUserEntity;

public interface CmsUserService {

    CmsUserEntity selectByUsername(String username);

}
