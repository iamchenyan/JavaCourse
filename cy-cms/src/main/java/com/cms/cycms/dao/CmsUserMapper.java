package com.cms.cycms.dao;

import com.cms.cycms.entity.CmsUserEntity;

public interface CmsUserMapper {

    CmsUserEntity getByUsername(String username);


}
