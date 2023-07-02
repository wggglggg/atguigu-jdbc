package com.atguigu.cms.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JDBCTools
 * Description: 数据库连接工具
 *
 * @Author wggglggg
 * @Create 2023/7/2 15:36
 * @Version 1.0
 */
public class JDBCTools {

    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();;
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 对外提供连接的方法
     * @return 数据库连接
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();

        if (connection == null){
            connection = dataSource.getConnection();
            tl.set(connection);
        }

        return connection;

    }

    /**
     * 接收连接，并将连接返回给连接池
     */
    public static void releaseConnection() throws SQLException {
        Connection connection = tl.get();

        if (connection != null) {
            tl.remove();
            connection.setAutoCommit(true); // 避免数据库连接池不自动提交
            connection.close();
        }
    }
}
