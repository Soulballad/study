package com.gupao.source.mebatis;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * @author Soulballad
 * @date 2019/5/6 21:02
 * @email soda931vzr@163.com
 * @description
 */
public class Configuration {

    public final static ResourceBundle sqlMappings;

    static {
        sqlMappings = ResourceBundle.getBundle("mesql");
    }

    /**
     * 返回接口的代理类对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz},
                new MapperProxy(sqlSession));
    }
}
