package com.gupao.source.spring.mock.springfamework.beans.support;

import com.gupao.source.spring.mock.springfamework.beans.config.GPBeanDefinition;
import com.gupao.source.spring.mock.springfamework.context.support.GPAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Soulballad
 * @date 2019/4/21 16:29
 * @email soda931vzr@163.com
 * @description
 */
public class DefaultListableBeanFactory extends GPAbstractApplicationContext {

    protected final Map<String, GPBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, GPBeanDefinition>();

    @Override
    public void refresh() throws Exception {
        super.refresh();
    }
}
