package com.gupao.source.spring.mock.springfamework.aop;

import com.gupao.source.spring.mock.springfamework.aop.intercept.GPMethodInvocation;
import com.gupao.source.spring.mock.springfamework.aop.support.GPAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Soulballad
 * @date 2019/5/2 12:48
 * @email soda931vzr@163.com
 * @description
 */
public class GPJdkDynamicAopProxy implements GPAopProxy, InvocationHandler {

    private GPAdvisedSupport advised;

    public GPJdkDynamicAopProxy(GPAdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {

        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {

        return Proxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> targetClass = this.advised.getTargetClass();
        List<Object> interceptionAdvice = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
        Object target = this.advised.getTarget();
        GPMethodInvocation invocation = new GPMethodInvocation(proxy, target, method, args, interceptionAdvice, targetClass);
        return invocation.proceed();
    }
}
