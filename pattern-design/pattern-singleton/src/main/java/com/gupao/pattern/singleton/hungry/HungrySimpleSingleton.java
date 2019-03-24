package com.gupao.pattern.singleton.hungry;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 10:48
 * @email soda931vzr@163.com
 */
public class HungrySimpleSingleton {

    private final static HungrySimpleSingleton singleton = new HungrySimpleSingleton();

    private HungrySimpleSingleton(){}

    public static HungrySimpleSingleton getInstance() {

        return singleton;
    }
}