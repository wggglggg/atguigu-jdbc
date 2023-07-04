package com.atguigu.cms.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/2 15:35
 * @Version 1.0
 */
public class BaseDAO {

    protected int  update(String sql, Object...args) throws SQLException {
        Connection connection = JDBCTools.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }

        }

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        if (connection.getAutoCommit()){
            JDBCTools.releaseConnection();
        }
        return rows;
    }

    protected <T> List<T> query(Class<T> clazz, String sql, Object...args) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JDBCTools.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (args != null && args.length > 0){
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
        }


        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<T> list = new ArrayList<>();

        while(resultSet.next()){
            T t = clazz.getDeclaredConstructor().newInstance();

            for (int i = 0; i < columnCount; i++) {
                Object columnValue = resultSet.getObject(i + 1);
                String columnLabel = rsmd.getColumnLabel(i + 1);

                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true);

                field.set(t, columnValue);

            }
            list.add(t);
        }

        resultSet.close();
        preparedStatement.close();

        if (connection.getAutoCommit()){
            JDBCTools.releaseConnection();
        }

        return list;
    }



}
