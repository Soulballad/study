package com.gupao.source.spring.orm.framework;


import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/5/4 14:44
 * @email soda931vzr@163.com
 * @description
 */
public class PropertyMapper<T> implements RowMapper {

    private Class<T> clazz;
    private Map<String, String> columnPropertyMap;

    public PropertyMapper(Class<T> clazz) {

        this.clazz = clazz;
        initMap();
    }

    private void initMap() {

        columnPropertyMap = new HashMap<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name().trim();
                columnPropertyMap.put(columnName, field.getName());
            } else {
                columnPropertyMap.put(field.getName(), field.getName());
            }
        }
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        try {
            Object instance = clazz.newInstance();
            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();
            String columnName;
            for (int i = 1; i <= columns; i++) {
                Object value = rs.getObject(i);
                columnName = meta.getColumnName(i);
                fillValue(instance, columnName, value);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void fillValue(Object instance, String columnName, Object value) throws Exception {

        Class<?> clazz = instance.getClass();
        Field field = clazz.getDeclaredField(columnPropertyMap.get(columnName));
        field.setAccessible(true);
        field.set(instance, value);
    }
}
