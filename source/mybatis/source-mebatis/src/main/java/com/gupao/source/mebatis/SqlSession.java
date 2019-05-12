package com.gupao.source.mebatis;

/**
 * @author Soulballad
 * @date 2019/5/7 21:07
 * @email soda931vzr@163.com
 * @description
 */
public class SqlSession {

    private Configuration configuration;//配置类
    private Executor executor;//执行器

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 获取一个代理对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }

    /**
     * 调用查询单条方法
     *
     * @param statementId
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId, Object parameter) {
        String sql = Configuration.sqlMappings.getString(statementId);
        return executor.query(sql, parameter);
    }
}
