package com.shirossm.mapper;

import com.github.pagehelper.Page;
import com.shirossm.pojo.Customer;

/**
* <p>Title: CustomerMapper</p>  
* @author chenyan  
* @date 2019年9月18日
 */
public interface CustomerMapper {

    void create(Customer customer);

    void delete(Long id);

    Customer findById(Long id);

    void update(Customer customer);

    Page<Customer> findByPage(Customer customer);

//    int selectCount();

//    List<Customer> findCondition(Map<String,Object> conMap);
}
