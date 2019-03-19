package com.gupao.pattern.proxy.staticproxy.example;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 20:54
 * @email soda931vzr@163.com
 * @description
 */
public class FatherTest {

    public static void main(String[] args) {

        Person person = new Father(new Son());
        person.findLove();
    }
}