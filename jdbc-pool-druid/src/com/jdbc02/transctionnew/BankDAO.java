package com.jdbc02.transctionnew;

import utils.JdbcUtilsV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: BankDAO
 * Description: 封装dao数据库重复代码!

 *
 * @Author wggglggg
 * @Create 2023/7/1 16:26
 * @Version 1.0
 */
public class BankDAO {

    /**
     * 往账号增加金额
     * @param account
     * @param money
     * @throws SQLException
     */
    public void addMoney(String account, int money) throws SQLException {

        //获取连接
        Connection connection = JdbcUtilsV2.getConnection();

        // 编译sql语句
        String sql = "update t_bank set money = money + ? where account = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);

        //发送SQL语句
        preparedStatement.executeUpdate();

        //关闭资源close
        preparedStatement.close();
    }

    /**
     * 扣除账号金额
     * @param account
     * @param money
     * @throws SQLException
     */
    public void subMoney(String account, int money) throws SQLException {

        //获取连接
        Connection connection = JdbcUtilsV2.getConnection();

        // 编译sql语句
        String sql = "update t_bank set money = money - ? where account = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);

        //发送SQL语句
        preparedStatement.executeUpdate();

        //关闭资源close
        preparedStatement.close();
    }
}
