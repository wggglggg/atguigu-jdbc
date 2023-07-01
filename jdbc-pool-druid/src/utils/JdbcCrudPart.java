package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName: JdbcCrudPart
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/1 14:24
 * @Version 1.0
 */
public class JdbcCrudPart {
    public void testInsert() throws SQLException {
        Connection connection = JdbcUtilsV1.getConnection();


        JdbcUtilsV1.releaseConnection(connection);
    }
}
