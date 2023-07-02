package com.atguigu.cms.service;


import com.atguigu.cms.dao.CustomerDAO;
import com.atguigu.cms.javabean.Customer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个具有管理功能的功能类. 内部数据不允许外部随意修改, 具有更好的封装性.
 */
public class CustomerService {

    CustomerDAO customerDAO = new CustomerDAO();

    /**
     * 用途：返回所有客户对象
     * 返回：集合
     */
    public List<Customer> getList() {

        try {
            return customerDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用途：添加新客户
     * 参数：customer指定要添加的客户对象
     */
    public void addCustomer(Customer customer) {
        try {
            customerDAO.addCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用途：返回指定id的客户对象记录
     * 参数： id 就是要获取的客户的id号.
     * 返回：封装了客户信息的Customer对象
     */
    public Customer getCustomer(int id) {

        try {
            return customerDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改指定id号的客户对象的信息
     * @param id 客户id
     * @param cust 对象
     * @return 修改成功返回true, false表明指定id的客户未找到
     */
    public boolean modifyCustomer(int id, Customer cust){

        int rows = 0;
        try {
            rows = customerDAO.updateById(cust);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (rows == 0) {
            return false;
        }
        return true;
    }

    /**
     * 用途：删除指定id号的的客户对象记录
     * 参数： id 要删除的客户的id号
     * 返回：删除成功返回true；false表示没有找到
     */
    public boolean removeCustomer(int id) {

        int rows = 0;
        try {
            rows = customerDAO.removeById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (rows == 0){
            return false;
        }
        return true;
    }

}
