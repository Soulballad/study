package com.gupao.pattern.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:47
 * @email soda931vzr@163.com
 * @description
 */
public interface GPInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}