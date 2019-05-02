package com.gupao.source.spring.mock.springfamework.aop.aspect;

import com.gupao.source.spring.mock.springfamework.aop.intercept.GPMethodInterceptor;
import com.gupao.source.spring.mock.springfamework.aop.intercept.GPMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/5/2 13:59
 * @email soda931vzr@163.com
 * @description
 */
public class GPAfterThrowingAdviceInterceptor extends GPAbstractAspectAdvice implements GPMethodInterceptor {

    private String throwName;

    public GPAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        } catch (Throwable tw) {
            invokeAdviceMethod(mi, null, tw);
            throw tw;
        }
    }

    public void setThrowName(String throwName) {
        this.throwName = throwName;
    }
}
