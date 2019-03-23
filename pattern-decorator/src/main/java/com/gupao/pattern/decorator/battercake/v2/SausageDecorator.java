package com.gupao.pattern.decorator.battercake.v2;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:54
 * @email soda931vzr@163.com
 * @description
 */
public class SausageDecorator extends BattercakeDecorator {

    public SausageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "1根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}