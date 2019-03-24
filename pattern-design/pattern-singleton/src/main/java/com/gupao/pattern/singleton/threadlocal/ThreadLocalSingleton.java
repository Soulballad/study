package com.gupao.pattern.singleton.threadlocal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 15:20
 * @email soda931vzr@163.com
 * @description 利用ThreadLocal创建单例，伪线程安全，对象在单个线程中是唯一的，多线程下无法保证单例
 */
public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> threadLocal = new ThreadLocal<ThreadLocalSingleton>(){
        @Override
        protected ThreadLocalSingleton initialValue() {

            return new ThreadLocalSingleton();
        }
    };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance() {

        return threadLocal.get();
    }
}