package com.gupao.source.spring.mvcframework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Soulballad
 * @date 2019/3/25/0025 22:09
 * @email soda931vzr@163.com
 * @description
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface GPRequestParam {
    String value() default "";
}