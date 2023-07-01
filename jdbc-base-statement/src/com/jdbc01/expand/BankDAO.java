package com.jdbc01.expand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * ClassName: BankDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/6/30 16:32
 * @Version 1.0
 */
public class BankDAO {

    public void addMoney(String account, int money, Connection connection) throws Exception {


        //预编译sql语句
        String sql = "UPDATE t_bank SET money = money + ? WHERE account = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);

        //发送SQL语句
        preparedStatement.executeUpdate();

        //关闭资源close, connection.close()放在BankService业务端写
        preparedStatement.close();
    }

    public void subMoney(String account, int money, Connection connection) throws Exception {


        //预编译sql语句
        String sql = "UPDATE t_bank SET money = money - ? WHERE account = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);

        //发送SQL语句
        preparedStatement.executeUpdate();

        //关闭资源close, connection.close()放在BankService业务端写
        preparedStatement.close();

    }


}
