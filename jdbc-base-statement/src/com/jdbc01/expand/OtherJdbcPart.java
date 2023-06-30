package com.jdbc01.expand;

import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * ClassName: OtherJdbcPart
 * Description:     扩展几种特殊情况
 *
 * @Author wggglggg
 * @Create 2023/6/30 14:07
 * @Version 1.0
 */
public class OtherJdbcPart {

    /**
     * 返回插入的主键！
     * 主键：数据库帮助维护的自增长的整数主键！
     * @throws Exception
     */
    @Test
    public void returnPrimaryKey() throws Exception {

        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc80", "root", "abc123");

        //3.编写SQL语句结构
        String sql = "INSERT INTO t_user(account, password, nickname) VALUES(?,?,?);";
        //4.创建预编译的statement，传入SQL语句结构
        /**
         * TODO: 第二个参数填入 1 | Statement.RETURN_GENERATED_KEYS
         *       告诉statement携带回数据库生成的主键！
         */
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //5.占位符赋值
        preparedStatement.setObject(1, "aliyun");
        preparedStatement.setObject(2, "aliyun");
        preparedStatement.setObject(3, "aliyun");

        //6.执行SQL语句 【注意：不需要传入SQL语句】 DML,只提交一行数据也只获取一条返回
        int rows = preparedStatement.executeUpdate();
        if (rows > 0){
            //一行一列的数据！里面就装主键值！
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();   // next()向下移动一次指针,才能获取那一行的primary key值
            int id = generatedKeys.getInt(1);
            //7.结果集解析
            System.out.println("id = " + id);
        } else {
            System.out.println("失败");
        }

        //8.释放资源
        preparedStatement.close();
        connection.close();
    }


    /**
     *
     * 批量细节：
     *    1.url?rewriteBatchedStatements=true
     *    2.insert 语句必须使用 values
     *    3.语句后面不能添加分号;
     *    4.语句不能直接执行，每次需要装货  addBatch() 最后 executeBatch();
     *
     * 批量插入优化！
     * @throws Exception
     */
    @Test
    public void testInsertMoreOnce() throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbc80?rewriteBatchedStatements=true", "root", "abc123");

        //3.编写SQL语句结构
        String sql = "INSERT INTO t_user(account, password, nickname) VALUES(?,?,?)";
        //4.创建预编译的statement，传入SQL语句结构
        PreparedStatement preparedStatement = connection.prepareStatement(sql);



        long start = System.currentTimeMillis();
        // 将数据传给占位符, 使用批处理的方法提交,批处理只是在sql语句后添加新的数据,不是提交到数据库   preparedStatement.addBatch();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setObject(1, "kuakeyun" + i);
            preparedStatement.setObject(2, "kuakeyun" + i);
            preparedStatement.setObject(3, "kuakeyun" + i);

            preparedStatement.addBatch();
        }

        //发车！ 批量操作！
        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();

        System.out.println("消耗时间："+(end - start));


        //7.结果集解析 略

        //8.释放资源
        preparedStatement.close();
        connection.close();
    }

}
