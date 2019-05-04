package com.gupao.source.spring.orm.framework;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Soulballad
 * @date 2019/5/4 13:49
 * @email soda931vzr@163.com
 * @description
 */
public class SQLGenerator<T> implements Serializable {

    private QueryRule queryRule;
    private Object t;
    private Class<T> clazz;

    public SQLGenerator(QueryRule queryRule, Object t) {
        this.queryRule = queryRule;
        this.t = t;
        this.clazz = (Class<T>) t.getClass();
    }

    public SQLGenerator(Object t) {
        this.t = t;
    }

    private String getSimpleSQL() {

        String tableName = "";
        StringBuilder columnBuilder = new StringBuilder();

        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            tableName = table.name().trim();
        } else {
            tableName = clazz.getSimpleName();
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columnBuilder.append(",").append(column.name()).append(",");
            } else {
                columnBuilder.append(",").append(field.getName()).append(",");
            }
        }

        String columns = columnBuilder.toString().replaceAll("(,)+", ",");
        String str = removeFirstAndLast(columns, ",");

        return "select " + str + " from " + tableName;
    }

    private String removeFirstAndLast(String columns, String s) {

        int begin = 0;
        int end = columns.length();

        if (columns.startsWith(s)) {
            begin = columns.indexOf(s) + 1;
        }

        if (columns.endsWith(s)) {
            end = columns.lastIndexOf(s);
        }

        return columns.substring(begin, end);
    }

    public String generateQuerySQL() {

        String simpleSQL = getSimpleSQL();

        List<QueryRule.Rule> ruleList = this.queryRule.getRuleList();
        StringBuilder builder = new StringBuilder();

        if (ruleList.isEmpty()) {
            return simpleSQL;
        }

        builder.append("where 1 = 1 ");

        for (QueryRule.Rule rule : ruleList) {

            String propertyName = rule.getPropertyName();
            String propertyValue = rule.getPropertyValue();
            String andOr = rule.getAndOr();
            String likeEqual = rule.getLikeEqual();

            builder.append(andOr).append(" ").append(propertyName).append(" ").append(likeEqual).append(" '").append(propertyValue).append("'");
        }

        return simpleSQL + " " + builder.toString();
    }
}
