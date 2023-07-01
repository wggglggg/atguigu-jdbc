package com.jdbc02.transctionnew;

import utils.JdbcUtilsV2;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: BankService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/1 16:26
 * @Version 1.0
 */
public class BankService {


    public void transfer(String addAccount, String subAccount, int money) throws SQLException {

        //获取连接
        Connection connection = null;

        int flag = 0;

        //利用try代码块,调用dao
        try {
            connection = JdbcUtilsV2.getConnection();

            //开启事务(关闭事务自动提交)
            connection.setAutoCommit(false);

            //调用加钱 和 减钱
            BankDAO bankDAO = new BankDAO();
            bankDAO.addMoney(addAccount, money);
            bankDAO.subMoney(subAccount, money);

            //不报错,提交事务
            connection.commit();
            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();

            //报错回滚事务
            connection.rollback();
            throw e;
        } finally {

            JdbcUtilsV2.releaseConnection(connection);
        }

        if (flag == 1){
            System.out.println("转账成功!");
        }else{
            System.out.println("转账失败!");
        }

    }
}
