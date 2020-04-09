package com.shirossm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirossm.mapper.RoleMapper;
import com.shirossm.pojo.Role;
import com.shirossm.service.RoleService;

/**
 * <p>Title: RoleServiceImpl</p>
 *
 * @author chenyan
 * @date 2019年9月26日
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
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
