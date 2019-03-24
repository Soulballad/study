package com.gupao.pattern.singleton.attack.reflect;

import java.lang.reflect.Constructor;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 11:59
 * @email soda931vzr@163.com
 * @description 静态内部类单例防御反射攻击，反射是通过构造函数来创建实例，那么需要判断实例是否已被创建，如果已创建则抛出异常
 */
public class LazyInnerClassSingletonAttackDefense {

    private LazyInnerClassSingletonAttackDefense() {

        synchronized (LazyInnerClassSingletonAttackDefense.class) {

            if (null != LazyHolder.singleton) {

                throw new RuntimeException("不能通过反射创建单例！");
            }
        }
    }

    public static LazyInnerClassSingletonAttackDefense getInstance() {

        return LazyHolder.singleton;
    }

    private static class LazyHolder {

        private final static LazyInnerClassSingletonAttackDefense singleton = new LazyInnerClassSingletonAttackDefense();
    }

    /**
     * 测试防御静态内部类单例的反射攻击
     * @param args
     */
    public static void main(String[] args) {

        Class<LazyInnerClassSingletonAttackDefense> innerClassSingletonClass = LazyInnerClassSingletonAttackDefense.class;

        try {

            Constructor<LazyInnerClassSingletonAttackDefense> constructor = innerClassSingletonClass.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            LazyInnerClassSingletonAttackDefense singleton1 = constructor.newInstance();

            System.out.println(singleton1);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}