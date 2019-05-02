package com.gupao.source.spring.mock.springfamework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Soulballad
 * @date 2019/4/21 12:17
 * @email soda931vzr@163.com
 * @description
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GPService {
    String value() default "";
}
