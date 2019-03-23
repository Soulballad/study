package com.gupao.pattern.decorator.battercake.v2;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:50
 * @email soda931vzr@163.com
 * @description
 */
public abstract class BattercakeDecorator extends Battercake{

    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }

    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }
}