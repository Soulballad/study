package com.gupao.source.spring.orm.framework;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;

/**
 * @author Soulballad
 * @date 2019/5/4 13:29
 * @email soda931vzr@163.com
 * @description
 */
public abstract class BaseDaoSupport<T extends Serializable>{

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private Class<T> clazz = GenericsUtils.getSuperClassGenricType(getClass(), 0);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public abstract void setDataSource(DataSource dataSource);

    protected List<T> selectList(QueryRule queryRule) throws Exception {

        String sql = new SQLGenerator<T>(queryRule, clazz.newInstance()).generateQuerySQL();

        List list = jdbcTemplate.query(sql, new PropertyMapper<T>(clazz));
        return list;
    }
}
