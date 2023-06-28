package com.jdbc01.jdbc;

import java.sql.*;

/**
 * ClassName: JdbcStatementQueryPart
 * Description:  利用jdbc技术,完成用户数据查询工作
 * TODO: 步骤总结 (6步)
 *  *    1. 注册驱动
 *  *    2. 获取连接
 *  *    3. 创建statement
 *  *    4. 发送SQL语句,并获取结果
 *  *    5. 结果集解析
 *  *    6. 关闭资源
 *
 * @Author wggglggg
 * @Create 2023/6/28 15:55
 * @Version 1.0
 */
public class JdbcStatementQueryPart {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        /**
         * TODO: 注意
         *   Driver -> com.mysql.cj.jdbc.Driver
         */
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        //2.获取连接
        /**
         * TODO: 注意
         *   面向接口编程
         *   java.sql 接口 = 实现类
         *   connection 使用java.sql.Connection接口接收
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc80",
                "root", "abc123");

        //3.创建小车
        Statement statement = conn.createStatement();

        //4.发送SQL语句
        String sql = "select id, account, password, nickname from t_user;";
        ResultSet resultSet = statement.executeQuery(sql);

        //5.结果集解析
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");

            System.out.println(id + "::" + account + "::" + password + "::" + nickname);

        }

        //6.关闭资源  【先开后关】
        resultSet.close();
        statement.close();
        conn.close();

    }
}
