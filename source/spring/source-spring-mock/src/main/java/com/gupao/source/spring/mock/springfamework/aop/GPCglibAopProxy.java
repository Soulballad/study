package com.gupao.source.spring.mock.springfamework.aop;

import com.gupao.source.spring.mock.springfamework.aop.support.GPAdvisedSupport;

/**
 * @author Soulballad
 * @date 2019/5/2 12:47
 * @email soda931vzr@163.com
 * @description
 */
public class GPCglibAopProxy implements GPAopProxy {

    public GPCglibAopProxy(GPAdvisedSupport config) {

    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
