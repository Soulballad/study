package com.gupao.source.spring.mvcframework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Soulballad
 * @date 2019/3/25/0025 20:19
 * @email soda931vzr@163.com
 * @description
 */

@Documented
@Target({TYPE, FIELD})
@Retention(RUNTIME)
public @interface GPAutowired {
    String value() default "";
}