package com.jdbc01.expand;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ClassName: BankService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/6/30 16:32
 * @Version 1.0
 */
public class BankService {

    @Test
    public void testTransfer() throws Exception {
        String addAccount = "tttttt";
        String subAccount = "dddddd";
        transfer(addAccount, subAccount, 500);
    }


    // 增加金额与扣款要在两个connection中，不出现一边金额增加了，另一边却没有足够的金额报错，下下面是优化代码
//    public void transfer(String addAccount, String subAccount, int money) throws Exception {
//
//        BankDAO bankDAO = new BankDAO();
//
//        bankDAO.addMoney(addAccount, money);
//        bankDAO.subMoney(subAccount, money);
//    }



    /**
     * 转账业务方法 增加金额与扣款要在一个connection中，不然会出现一边金额增加了，另一边却没有足够的金额报错
     * @param addAccount  加钱账号
     * @param subAccount  减钱账号
     * @param money  金额
     */
    public void transfer(String addAccount, String subAccount, int money) throws Exception {

        System.out.println("addAccount = " + addAccount + ", subAccount = " + subAccount + ", money = " + money);
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        String url = "jdbc:mysql:///jdbc80";
        Connection connection = DriverManager.getConnection(url, "root", "abc123");

        int flag = 0;

        //利用try代码块,调用dao
        try {
            //开启事务(关闭事务自动提交)
            connection.setAutoCommit(false);

            //调用加钱 和 减钱
            BankDAO bankDAO = new BankDAO();
            bankDAO.addMoney(addAccount, money, connection);
            System.out.println("--------------");
            bankDAO.subMoney(subAccount, money, connection);

            //不报错,提交事务
            connection.commit();

            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();

            //报错回滚事务
            connection.rollback();
            throw e;

        } finally {
            connection.close();
        }

        if (flag > 0){
            System.out.println("转账成功");
        } else {
            System.out.println("失败");
        }
    }
}
