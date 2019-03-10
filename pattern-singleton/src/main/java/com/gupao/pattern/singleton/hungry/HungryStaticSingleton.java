package com.gupao.pattern.singleton.hungry;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 10:48
 * @email soda931vzr@163.com
 * @description 饿汉式的静态写法，通过静态代码块对对象进行初始化
 */
public class HungryStaticSingleton {

    private static final HungryStaticSingleton singleton;

    static {

        singleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {}

    public static HungryStaticSingleton getInstance() {

        return singleton;
    }
}