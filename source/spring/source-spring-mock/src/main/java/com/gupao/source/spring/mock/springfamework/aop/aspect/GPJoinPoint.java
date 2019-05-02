package com.gupao.source.spring.mock.springfamework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/5/2 15:28
 * @email soda931vzr@163.com
 * @description
 */
public interface GPJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
