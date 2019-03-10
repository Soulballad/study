package com.gupao.pattern.singleton.lazy;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 11:04
 * @email soda931vzr@163.com
 * @description 懒汉式，给getInstance()加锁，同一时刻只能有一个线程进入到getInstance方法，其余的线程需要等待，相当于把多线程变成顺序执行，可以保证单例，但性能低下
 */
public class LazySimpleSynchronizedSingleton {

    private static LazySimpleSynchronizedSingleton singleton = null;

    private LazySimpleSynchronizedSingleton(){}

    public static synchronized LazySimpleSynchronizedSingleton getInstance() {

        if (null == singleton) {

            singleton = new LazySimpleSynchronizedSingleton();
        }

        return singleton;
    }
}