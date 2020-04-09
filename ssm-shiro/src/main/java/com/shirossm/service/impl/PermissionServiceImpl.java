package com.shirossm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirossm.mapper.PermissionMapper;
import com.shirossm.pojo.Permission;
import com.shirossm.service.PermissionService;

/**
 * <p>Title: PermissionServiceImpl</p>
 *
 * @author chenyan
 * @date 2019年9月26日
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public Object findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void create(Object t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Object t) {
        // TODO Auto-generated method stub

    }

}
