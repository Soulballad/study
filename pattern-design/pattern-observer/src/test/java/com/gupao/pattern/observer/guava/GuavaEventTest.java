package com.gupao.pattern.observer.guava;

import com.google.common.eventbus.EventBus;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 17:25
 * @email soda931vzr@163.com
 * @description
 */
public class GuavaEventTest {

    public static void main(String[] args) {

        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("tom");
    }
}