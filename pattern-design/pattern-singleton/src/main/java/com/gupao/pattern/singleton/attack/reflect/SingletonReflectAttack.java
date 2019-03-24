package com.gupao.pattern.singleton.attack.reflect;

import com.gupao.pattern.singleton.lazy.LazyInnerClassSingleton;

import java.lang.reflect.Constructor;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 11:44
 * @email soda931vzr@163.com
 * @description 通过反射对单例进行攻击，发现创建了2个对象，单例被破坏
 */
public class SingletonReflectAttack {

    public static void main(String[] args) {

        Class<LazyInnerClassSingleton> innerClassSingletonClass = LazyInnerClassSingleton.class;

        try {
            // 构造函数被私有化，只能通过强制访问来调用
            Constructor<LazyInnerClassSingleton> constructor = innerClassSingletonClass.getDeclaredConstructor(null);

            constructor.setAccessible(true);
            LazyInnerClassSingleton singleton1 = constructor.newInstance();
            LazyInnerClassSingleton singleton2 = constructor.newInstance();
            LazyInnerClassSingleton singleton3 = LazyInnerClassSingleton.getInstance();

            System.out.println(singleton1);
            System.out.println(singleton2);
            System.out.println(singleton3);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}