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
public class GPAfterReturnAdviceInterceptor extends GPAbstractAspectAdvice implements GPMethodInterceptor {

    private GPJoinPoint joinPoint;

    public GPAfterReturnAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }


    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {

        Object returnVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturn(returnVal, mi.getMethod(), mi.getArguments(), mi.getThis());
        return returnVal;
    }

    private void afterReturn(Object returnVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint, returnVal, null);
    }
}
