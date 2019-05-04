package com.gupao.source.spring.orm;

import com.gupao.source.spring.orm.demo.entity.Member;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/5/3 15:55
 * @email soda931vzr@163.com
 * @description
 */
public class TestJdbc {

    @Test
    public void test1() throws ClassNotFoundException {

        Member member = new Member();
        member.setAge(22);
        member.setName("tom");
        List<?> selectList = selectList(member);
        System.out.println(Arrays.toString(selectList.toArray()));
    }

    private List<Object> selectList(Object condition) throws ClassNotFoundException {

        Class<?> entityClass = condition.getClass();

        List<Object> resultList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://192.168.31.201:3306/spring-orm-demo-01?characterEncoding=UTF-8&rewriteBatchedStatements=true",
                    "root", "123456");

            Field[] fields = entityClass.getDeclaredFields();
            Map<String, String> mapper = new HashMap<>();
            Map<String, String> columnFieldMap = new HashMap<>();
            StringBuffer buffer = new StringBuffer(" where 1 = 1");

            for (Field field : fields) {

                String fieldName = field.getName();
                if (!field.isAnnotationPresent(Column.class)) {
                    mapper.put(fieldName, fieldName);
                    columnFieldMap.put(fieldName, fieldName);
                    continue;
                }
                Column column = field.getAnnotation(Column.class);
                mapper.put(column.name().trim(), fieldName);
                columnFieldMap.put(fieldName, column.name().trim());
            }


            Table table = entityClass.getAnnotation(Table.class);

            String sql = "select * from " + table.name().trim();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(condition);

                if (null == value) {
                    continue;
                }

                if (String.class == field.getType()) {
                    buffer.append(" and " + columnFieldMap.get(field.getName()) + " = '" + value + "'");
                } else {
                    buffer.append(" and " + columnFieldMap.get(field.getName()) + " = " + value);
                }
            }

            sql = sql + buffer.toString();
            System.err.println(sql);
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            //元数据，保存除了真正数值以外的所有附加信息
            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                Object instance = entityClass.newInstance();

                for (int i = 1; i <= columnCount; i++) {

                    String columnName = rs.getMetaData().getColumnName(i);
                    Field field = entityClass.getDeclaredField(mapper.get(columnName));
                    field.setAccessible(true);
                    field.set(instance, rs.getObject(columnName));
                }

//                Member member = packageMember(rs, rs.getRow());
                resultList.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }

    private Member packageMember(ResultSet rs, int row) {

        Member m = new Member();
//        m.setId(rs.getLong("id"));
//        m.setAddr(rs.getString("addr"));
//        m.setAge(rs.getInt("age"));
//        m.setName(rs.getString("name"));
        return m;
    }
}
