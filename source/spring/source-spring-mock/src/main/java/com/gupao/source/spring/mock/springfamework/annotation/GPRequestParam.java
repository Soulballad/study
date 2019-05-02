package com.gupao.source.spring.mock.springfamework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Soulballad
 * @date 2019/4/21 12:20
 * @email soda931vzr@163.com
 * @description
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface GPRequestParam {
    String value() default "";
}
