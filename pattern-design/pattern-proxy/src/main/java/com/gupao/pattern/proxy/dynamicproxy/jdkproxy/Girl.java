package com.gupao.pattern.proxy.dynamicproxy.jdkproxy;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:27
 * @email soda931vzr@163.com
 * @description
 */
public class Girl implements Person {

    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高180cm");
        System.out.println("有6块腹肌");
    }
}