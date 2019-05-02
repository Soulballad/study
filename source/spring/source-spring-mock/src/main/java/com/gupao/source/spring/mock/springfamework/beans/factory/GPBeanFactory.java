package com.gupao.source.spring.mock.springfamework.beans.factory;

/**
 * @author Soulballad
 * @date 2019/4/21 16:13
 * @email soda931vzr@163.com
 * @description
 */
public interface GPBeanFactory {

    Object getBean(String beanName) throws Exception;

    Object getBean(Class<?> beanClass) throws Exception;
}
