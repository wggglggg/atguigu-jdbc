package utils;

import org.junit.Test;


import java.sql.*;
import java.util.List;


/**
 * ClassName: PSCURDPart
 * Description:     继承BaseDAO后，再次curd练习
 *
 * @Author wggglggg
 * @Create 2023/7/2 13:18
 * @Version 1.0
 */
public class PSCURDPart extends BaseDAO{

   /**
    * 插入一条用户数据!
    * @throws ClassNotFoundException
    * @throws SQLException
    */
   @Test
   public void testInset() throws ClassNotFoundException, SQLException {

      String sql = "insert into t_user(account, password, nickname) values(?,?,?);";

      int rows = executeUpdate(sql, "AMD", 777, "农企");
      System.out.println(rows);

   }

   /**
    * 修改一条用户数据! 使用配置与代码分离的方式，从jdbc.properties配置文件读取配置
    * 修改账号: guest的用户,将nickname改为tomcat
    * @throws Exception
    */
   @Test
   public void testUpdate() throws Exception {


      //TODO: 切记, ? 只能代替 值!!!!!  不能代替关键字 特殊符号 容器名
      String sql = "UPDATE t_user SET nickname = ? WHERE account = ? ;";
      int rows = executeUpdate(sql, "高科技芯片公司", "AMD");

      System.out.println(rows);

   }

   /**
    * 删除一条用户数据!
    * 根据账号: guest
    * @throws Exception
    */
   @Test
   public void testDelete() throws Exception {

      //TODO: 切记, ? 只能代替 值!!!!!  不能代替关键字 特殊符号 容器名
      String sql = "Delete From t_user WHERE account = ?;";
      int rows = executeUpdate(sql, "aliyun");
      System.out.println(rows);
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
   public void testQuery() throws Exception {


      //TODO: 切记, ? 只能代替 值!!!!!  不能代替关键字 特殊符号 容器名
      String sql = "SELECT id, account, password, nickname from t_user;";


      List<User> users = executeQuery(User.class, sql);

      for (User u :
              users) {
         System.out.println(u);
      }

   }
}
