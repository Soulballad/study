package com.gupao.source.spring.mock.springfamework.beans;

/**
 * @author Soulballad
 * @date 2019/4/22 20:22
 * @email soda931vzr@163.com
 * @description
 */
public class GPBeanWrapper {

    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public GPBeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance() {

        return this.wrappedInstance;
    }

    public Class<?> getWrappedClass() {

        return this.wrappedInstance.getClass();
    }
}
