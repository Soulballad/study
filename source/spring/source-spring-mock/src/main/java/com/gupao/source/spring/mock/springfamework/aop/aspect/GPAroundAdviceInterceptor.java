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
public class GPAroundAdviceInterceptor extends GPAbstractAspectAdvice implements GPMethodInterceptor {

    public GPAroundAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }


    @Override
    public Object invoke(GPMethodInvocation mi) throws Throwable {

        System.out.println("around invoke before......");
        Object returnVal = mi.proceed();
        this.invokeAdviceMethod(mi, returnVal, null);
        System.out.println("around invoke after......");
        return returnVal;
    }
}
