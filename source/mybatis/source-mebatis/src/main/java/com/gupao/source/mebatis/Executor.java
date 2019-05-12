package com.gupao.source.mebatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Soulballad
 * @date 2019/5/6 21:06
 * @email soda931vzr@163.com
 * @description
 */
public class Executor {

    public <T> T query(String sql, Object parameter) {

        Connection conn = null;
        Statement statement = null;

        try {

            Blog blog = new Blog();

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://192.168.31.201:3306/spring-orm-demo-01?characterEncoding=UTF-8&rewriteBatchedStatements=true",
                    "root", "123456");

            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(String.format(sql, parameter));

            while (rs.next()) {
                blog.setId(rs.getInt("id"));
                blog.setName(rs.getString("name"));
            }

            return (T) blog;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
