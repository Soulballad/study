package com.gupao.pattern.proxy.dynamicproxy.gpproxy;

import com.gupao.pattern.proxy.dynamicproxy.jdkproxy.Girl;
import com.gupao.pattern.proxy.dynamicproxy.jdkproxy.JDKMeipo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 22:26
 * @email soda931vzr@163.com
 * @description
 */
public class GPProxyTest {


    public static void main(String[] args) throws Exception {

        Object instance = new GPMeipo().getInstance(new Girl());
        Method method = instance.getClass().getDeclaredMethod("findLove");
        method.invoke(instance);
    }
}