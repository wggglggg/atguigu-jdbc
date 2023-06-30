package com.jdbc01.jdbc;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;


/**
 * ClassName: JdbcPreparedStatementCrudPart
 * Description:     再次加强curd练习
 *
 * @Author wggglggg
 * @Create 2023/6/30 9:03
 * @Version 1.0
 */
public class JdbcPreparedStatementCrudPart {

   /**
    * 插入一条用户数据!
    * @throws ClassNotFoundException
    * @throws SQLException
    */
   @Test
   public void testInset() throws ClassNotFoundException, SQLException {
      //注册驱动
      Class.forName("com.mysql.cj.jdbc.Driver");

      //获取连接
      String url = "jdbc:mysql:///jdbc80";
      Connection connection = DriverManager.getConnection(url, "root", "abc123");

      String sql = "insert into t_user(account, password, nickname) values(?,?,?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      //占位符赋值
      preparedStatement.setObject(1, "guest");
      preparedStatement.setObject(2, "guest");
      preparedStatement.setObject(3, "guest");

      //发送SQL语句
      int rows = preparedStatement.executeUpdate();
      //输出结果
      System.out.println(rows);

      //关闭资源close
      preparedStatement.close();
      connection.close();

   }

   /**
    * 修改一条用户数据! 使用配置与代码分离的方式，从jdbc.properties配置文件读取配置
    * 修改账号: guest的用户,将nickname改为tomcat
    * @throws Exception
    */
   @Test
   public void testUpdate() throws Exception {
      // 读取配置
      InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

      Properties properties = new Properties();
      properties.load(is);

      String user = properties.getProperty("user");
      String password = properties.getProperty("password");
      String url = properties.getProperty("url");
      String driverClass = properties.getProperty("driverClass");

      //注册驱动
      Class.forName(driverClass);

      //获取连接
      Connection connection = DriverManager.getConnection(url, user, password);

      //TODO: 切记, ? 只能代替 值!!!!!  不能代替关键字 特殊符号 容器名
      String sql = "UPDATE t_user SET nickname = ? WHERE account = ? ;";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      //占位符赋值
      preparedStatement.setObject(1, "tomcat");
      preparedStatement.setObject(2, "guest");

      //发送SQL语句
      int rows = preparedStatement.executeUpdate();
      //输出结果
      System.out.println(rows);

      //关闭资源close
      preparedStatement.close();
      connection.close();
   }

   /**
    * 删除一条用户数据!
    * 根据账号: guest
    * @throws Exception
    */
   @Test
   public void testDelete() throws Exception {
      InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

      Properties properties = new Properties();
      properties.load(is);

      String user = properties.getProperty("user");
      String password = properties.getProperty("password");
      String url = properties.getProperty("url");
      String driverClass = properties.getProperty("driverClass");

      //注册驱动
      Class.forName(driverClass);

      //获取连接
      Connection connection = DriverManager.getConnection(url, user, password);

      //TODO: 切记, ? 只能代替 值!!!!!  不能代替关键字 特殊符号 容器名
      String sql = "Delete From t_user WHERE account = ?;";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);


      //占位符赋值
      preparedStatement.setObject(1, "guest");

      //发送SQL语句
      int rows = preparedStatement.executeUpdate();
      //输出结果
      System.out.println(rows);

      //关闭资源close
      preparedStatement.close();
      connection.close();
   }


   /**
    * 查询全部数据!
    *   将数据存到List<Map>中
    *   map -> 对应一行数据
    *      map key -> 数据库列名或者别名
    *      map value -> 数据库列的值
    * TODO: 思路分析
    *    1.先创建一个List<Map>集合
    *    2.遍历resultSet对象的行数据
    *    3.将每一行数据存储到一个map对象中!
    *    4.将对象存到List<Map>中
    *    5.最终返回
    *
    * TODO:
    *    初体验,结果存储!
    *    学习获取结果表头信息(列名和数量等信息)
    */
   @Test
   public void testQueryMap() throws Exception {
      InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

      Properties properties = new Properties();
      properties.load(is);

      String user = properties.getProperty("user");
      String password = properties.getProperty("password");
      String url = properties.getProperty("url");
      String driverClass = properties.getProperty("driverClass");

      //注册驱动
      Class.forName(driverClass);

      //获取连接
      Connection connection = DriverManager.getConnection(url, user, password);

      //TODO: 切记, ? 只能代替 值!!!!!  不能代替关键字 特殊符号 容器名
      String sql = "SELECT id, account, password, nickname from t_user;";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      //占位符赋值 本次没有占位符,省略

      //发送查询语句
      ResultSet resultSet = preparedStatement.executeQuery();

      // 通过getMetaData转成元数据，
      ResultSetMetaData metaData = resultSet.getMetaData();
      // 元数据中getColumnCount存有第一行有几个column
      int columnCount = metaData.getColumnCount();

      //创建一个集合
      List<Map> mapList = new ArrayList<>();

      //获取列信息对象,
      while(resultSet.next()){
         Map map = new HashMap();
         // 元数据中getColumnCount存有用记查询了几列数据
         for (int i = 1; i <= columnCount; i++){
            Object columnValue = resultSet.getObject(i);
            // metaData.getColumnLabel存有表头名或者别名值
            String columnName = metaData.getColumnLabel(i);
            // 将第一行单个列数据存入map中
            map.put(columnName, columnValue);
         }
         // 将每一行map存入List集合中
         mapList.add(map);
      }

      //输出结果
      System.out.println(mapList);

      //关闭资源close
      resultSet.close();
      preparedStatement.close();
      connection.close();

   }

}
