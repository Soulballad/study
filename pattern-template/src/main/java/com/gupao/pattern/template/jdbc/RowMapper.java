package com.gupao.pattern.template.jdbc;

import java.sql.ResultSet;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:08
 * @email soda931vzr@163.com
 * @description
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs, int rowNum) throws Exception;
}