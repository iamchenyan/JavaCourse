package com.shirossm.service;

import com.shirossm.pojo.Customer;
import com.shirossm.pojo.PageBean;

/**
* <p>Title: CustomerService</p>  
* @author chenyan  
* @date 2019年9月18日
 */
public interface CustomerService extends BaseService<Customer> {

    /**
     * 分页查询
     * @param customer 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(Customer customer, int pageCode, int pageSize);

}
