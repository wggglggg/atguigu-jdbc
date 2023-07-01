package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jdbc01.druid.DruidUsePart;
import org.junit.Test;

import javax.sql.DataSource;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtilsV1
 * Description:
 * v1.0版本工具类
 *   内部包含一个连接池对象,并且对外提供获取连接和回收连接的方法!
 *
 * 小建议:
 *   工具类的方法,推荐写成静态,外部调用会更加方便!
 *
 * 实现:
 *   属性 连接池对象 [实例化一次]
 *       单例模式
 *       static{
 *           全局调用一次
 *       }
 *   方法
 *       对外提供连接的方法
 *       回收外部传入连接方法
 *
 *
 * @Author wggglggg
 * @Create 2023/7/1 13:46
 * @Version 1.0
 */

// static{
// *           全局调用一次
// *       }
public class JdbcUtilsV1 {

    private static DataSource dataSource = null; //连接池对象

    static {
        //初始化连接池对象
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对外提供连接的方法
     * @return
     */
    public static Connection getConnection() throws SQLException {

        return dataSource.getConnection();
    }

    /**
     * 接收连接，并将连接返回给连接池
     */
    public static void releaseConnection(Connection connection) throws SQLException {

        connection.close();     //回收到连接池即可
    }


    @Test
    public void testJdbcUtils() throws SQLException {
        Connection connection = getConnection();


        String sql = "update t_user set nickname = ? where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3. 给占位符赋值
        preparedStatement.setObject(1, "alibaba");
        preparedStatement.setObject(2, 4);



        // 4. 执行 成功的数据条数被 rows接收
        int rows = preparedStatement.executeUpdate();

        // 5.打印成功条数
        System.out.println(rows);

        releaseConnection(connection);
    }
}
