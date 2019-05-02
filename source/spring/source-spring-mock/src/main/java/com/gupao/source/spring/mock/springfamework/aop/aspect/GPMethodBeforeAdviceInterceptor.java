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
public class GPMethodBeforeAdviceInterceptor extends GPAbstractAspectAdvice implements GPMethodInterceptor {

    private GPJoinPoint joinPoint;

    public GPMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method, Object target, Object[] args) throws Throwable {
//        method.invoke(target, args);
        super.invokeAdviceMethod(this.joinPoint, null, null);
    }

    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getThis(), mi.getArguments());
        return mi.proceed();
    }
}
