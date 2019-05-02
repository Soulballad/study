package com.gupao.source.spring.mock.springfamework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/5/2 14:42
 * @email soda931vzr@163.com
 * @description
 */
public class GPAbstractAspectAdvice implements GPAdvice {

    private Method aspectMethod;

    private Object aspectTarget;

    public GPAbstractAspectAdvice(Method aspectMethod, Object aspectTarget) {

        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }

    protected void invokeAdviceMethod(GPJoinPoint joinPoint, Object returnValue, Throwable ex) throws Throwable{
        Class<?>[] parameterTypes = this.aspectMethod.getParameterTypes();
        if (null == parameterTypes || parameterTypes.length == 0) {
            this.aspectMethod.invoke(this.aspectTarget);
        }else{
            Object[] args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                if (GPJoinPoint.class == parameterType) {
                    args[i] = joinPoint;
                } else if (Object.class == parameterType) {
                    args[i] = returnValue;
                } else if (Throwable.class == parameterType) {
                    args[i] = ex;
                }
            }
            this.aspectMethod.invoke(this.aspectTarget, args);
        }
    }
}
