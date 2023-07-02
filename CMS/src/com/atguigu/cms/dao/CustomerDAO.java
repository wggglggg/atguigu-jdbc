package com.atguigu.cms.dao;

import com.atguigu.cms.javabean.Customer;
import com.atguigu.cms.utils.BaseDAO;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: CustomerDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/2 16:30
 * @Version 1.0
 */
public class CustomerDAO extends BaseDAO {

    public List<Customer> findAll() throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String sql = "select * from t_customer";
        List<Customer> list = query(Customer.class, sql);
        return list;

    }

    public void addCustomer(Customer customer) throws SQLException {
        String sql = "insert into t_customer(name, gender, age, salary, phone) values(?,?,?,?,?); ";
        update(sql, customer.getName(), customer.getGender(), customer.getAge(),
                customer.getSalary(), customer.getPhone());
    }

    public  Customer findById(int id) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String sql = "select * from t_customer where id = ?";
        List<Customer> list = query(Customer.class, sql, id);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;

    }

    public int updateById(Customer customer) throws SQLException {
        String sql = "update t_customer set name=?, gender=?, age=?, salary=?, phone=? where id = ?;";
        int rows = update(sql, customer.getName(), customer.getGender(), customer.getAge(),
                customer.getSalary(), customer.getPhone(), customer.getId());
        return rows;
    }

    public int removeById(int id) throws SQLException {
        String sql = "delete from t_customer where id = ?";
        int rows = update(sql, id);
        return rows;

    }
}
