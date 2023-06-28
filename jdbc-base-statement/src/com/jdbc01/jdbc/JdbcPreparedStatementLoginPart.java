package com.jdbc01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * ClassName: JdbcPreparedStatementLoginPart
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/6/28 16:05
 * @Version 1.0
 */
public class JdbcPreparedStatementLoginPart {
    public static void main(String[] args) throws Exception {
        //1.输入账号和密码
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入账号:");
        String account = scanner.nextLine();
        System.out.print("请输入密码:");
        String password = scanner.nextLine();

        //2.jdbc的查询使用
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc80", "root", "abc123");

        //创建preparedStatement
        //connection.createStatement();
        //TODO 需要传入SQL语句结构
        //TODO 要的是SQL语句结构，动态值的部分使用 ? ,  占位符！
        //TODO ?  不能加 '?'  ? 只能替代值，不能替代关键字和容器名
        String sql = "select * from t_user where account = ? and password = ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        //占位符赋值
        //给占位符赋值！ 从左到右，从1开始！
        /**
         *  int 占位符的下角标
         *  object 占位符的值, 防注入SQL
         */
        preparedStatement.setObject(1, account);
        preparedStatement.setObject(2, password);

        //这哥们内部完成SQL语句拼接！
        //执行SQL语句即可
        ResultSet resultSet = preparedStatement.executeQuery();

        //preparedStatement.executeUpdate()

        //进行结果集对象解析
        if (resultSet.next()) {
            //只要向下移动，就是有数据 就是登录成功！
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败！");
        }

        //关闭资源
        resultSet.close();
        preparedStatement.close();
        conn.close();

    }
}
