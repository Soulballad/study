package com.gupao.source.spring.mock.springfamework.aop.intercept;

/**
 * @author Soulballad
 * @date 2019/5/2 13:10
 * @email soda931vzr@163.com
 * @description
 */
public interface GPMethodInterceptor {
    Object invoke(GPMethodInvocation invocation) throws Throwable;
}
