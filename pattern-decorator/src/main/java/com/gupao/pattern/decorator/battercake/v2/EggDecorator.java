package com.gupao.pattern.decorator.battercake.v2;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:53
 * @email soda931vzr@163.com
 * @description
 */
public class EggDecorator extends BattercakeDecorator {

    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}