package com.gupao.source.mebatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/5/6 21:03
 * @email soda931vzr@163.com
 * @description
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String statementId = method.getDeclaringClass().getName() + "." + method.getName();
        return this.sqlSession.selectOne(statementId, args[0]);
    }
}
