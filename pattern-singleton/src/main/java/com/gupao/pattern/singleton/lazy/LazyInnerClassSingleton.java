package com.gupao.pattern.singleton.lazy;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 11:35
 * @email soda931vzr@163.com
 * @description 使用静态内部类来创建单例，内部类在父类初始化之前初始化完成
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){}

    public static LazyInnerClassSingleton getInstance() {

        return LazyHolder.singleton;
    }

    private static class LazyHolder{

        private final static LazyInnerClassSingleton singleton = new LazyInnerClassSingleton();
    }
}