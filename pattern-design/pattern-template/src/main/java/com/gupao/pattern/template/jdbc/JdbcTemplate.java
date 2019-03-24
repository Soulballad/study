package com.gupao.pattern.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:09
 * @email soda931vzr@163.com
 * @description
 */
public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] objects) throws Exception {
        //1、获取连接
        Connection connection = this.getConnection();
        //2、创建语句集
        PreparedStatement statement = this.createPrepareStatement(connection, sql);
        //3、执行语句
        ResultSet rs = this.executeQuery(statement, objects);
        //4、处理结果集
        List<?> list = this.prepareResultSet(rs, rowMapper);
        //5、关闭结果集
        this.closeResultSet(rs);
        //6、关闭语句集
        this.closeStatement(statement);
        //7、关闭连接
        this.closeConnection(connection);

        return list;
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    private void closeStatement(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    private void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    private List<?> prepareResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {

        List<Object> list = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) {
            list.add(rowMapper.mapRow(rs, rowNum++));
        }

        return list;
    }

    private ResultSet executeQuery(PreparedStatement statement, Object[] objects) throws SQLException {

        for (int i = 0; i < objects.length; i++) {

            statement.setObject(i, objects[i]);
        }
        return statement.executeQuery();
    }

    private PreparedStatement createPrepareStatement(Connection connection, String sql) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sql);
        return statement;
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}