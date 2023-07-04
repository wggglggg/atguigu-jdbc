package com.jdbc01.druid;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;


import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: DruidUsePart
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/1 8:52
 * @Version 1.0
 */
public class DruidUsePart {



    @Test
    public void testHard() throws Exception {
        // 1. 创建连接池
        DruidDataSource druidDataSource = new DruidDataSource();

        // 2. 配置连接数据必备信息
        druidDataSource.setUrl("jdbc:mysql:///jdbc80");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("abc123");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        druidDataSource.setInitialSize(5);
        druidDataSource.setMaxActive(10);

        // 3. 创建连接， 连接数据库
        Connection connection = druidDataSource.getConnection();

        // 关闭资源
        connection.close();
        druidDataSource.close();
//
    }

    @Test
    public void testSoft() throws Exception {
        // 1.读取外部配置文件， 并加载配置
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

        Properties properties = new Properties();
        properties.load(is);

        // 2.创建连接池的工具类工程模式， 创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();

        // 编译sql语句
        String sql = "update t_user set nickname = ? where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 给占位符赋值
        preparedStatement.setObject(1, "ali");
        preparedStatement.setObject(2, 4);

        // 4. 执行 成功的数据条数被 rows接收
        int rows = preparedStatement.executeUpdate();

        // 5.打印成功条数
        System.out.println(rows);

        // 关闭资源
        preparedStatement.close();
        connection.close();
    }


}
