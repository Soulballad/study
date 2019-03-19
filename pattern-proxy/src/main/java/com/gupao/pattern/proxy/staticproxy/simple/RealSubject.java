package com.gupao.pattern.proxy.staticproxy.simple;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:01
 * @email soda931vzr@163.com
 * @description
 */
public class RealSubject implements Subject {

    @Override
    public void request() {

        System.out.println("real service is called.");
    }
}