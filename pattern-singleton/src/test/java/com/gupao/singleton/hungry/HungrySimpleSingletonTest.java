package com.gupao.singleton.hungry;

import com.gupao.pattern.singleton.hungry.HungrySimpleSingleton;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 10:48
 * @email soda931vzr@163.com
 * @description 饿汉式单例的常见写法
 */
public class HungrySimpleSingletonTest {

    public static void main(String[] args) {

        HungrySimpleSingleton instance1 = HungrySimpleSingleton.getInstance();
        HungrySimpleSingleton instance2 = HungrySimpleSingleton.getInstance();
        HungrySimpleSingleton instance3 = HungrySimpleSingleton.getInstance();
        HungrySimpleSingleton instance4 = HungrySimpleSingleton.getInstance();

        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);
        System.out.println(instance4);
    }
}