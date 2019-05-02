package com.gupao.source.spring.mock.springfamework.aop;

/**
 * @author Soulballad
 * @date 2019/5/2 12:47
 * @email soda931vzr@163.com
 * @description
 */
public interface GPAopProxy {

    public Object getProxy();

    public Object getProxy(ClassLoader classLoader);
}
