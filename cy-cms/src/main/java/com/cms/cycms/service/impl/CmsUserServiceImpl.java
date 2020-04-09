package com.cms.cycms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.cycms.dao.CmsUserMapper;
import com.cms.cycms.entity.CmsUserEntity;
import com.cms.cycms.service.api.CmsUserService;

@Service
public class CmsUserServiceImpl implements CmsUserService {

    @Autowired
    private CmsUserMapper cmsUserMapper;

    @Override
    public CmsUserEntity selectByUsername(String username) {
        return null;
    }

}
