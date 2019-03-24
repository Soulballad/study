package com.gupao.pattern.observer.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 17:23
 * @email soda931vzr@163.com
 * @description
 */
public class GuavaEvent {

    @Subscribe
    public void subscribe(String parameter) {
        System.out.println("执行subscribe方法，参数是：" + parameter);
    }
}