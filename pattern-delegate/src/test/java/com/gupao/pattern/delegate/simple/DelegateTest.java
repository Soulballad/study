package com.gupao.pattern.delegate.simple;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 22:27
 * @email soda931vzr@163.com
 * @description
 */
public class DelegateTest {

    public static void main(String[] args) {

        Boss boss = new Boss();
        boss.command("登录", new Leader());
    }
}