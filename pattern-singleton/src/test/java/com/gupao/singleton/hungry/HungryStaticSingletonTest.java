package com.gupao.singleton.hungry;

import com.gupao.pattern.singleton.hungry.HungryStaticSingleton;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 10:59
 * @email soda931vzr@163.com
 */
public class HungryStaticSingletonTest {

    public static void main(String[] args) {

        HungryStaticSingleton instance1 = HungryStaticSingleton.getInstance();
        HungryStaticSingleton instance2 = HungryStaticSingleton.getInstance();
        HungryStaticSingleton instance3 = HungryStaticSingleton.getInstance();
        HungryStaticSingleton instance4 = HungryStaticSingleton.getInstance();

        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);
        System.out.println(instance4);
    }
}