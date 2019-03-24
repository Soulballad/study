package com.gupao.pattern.decorator.battercake.v1;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:42
 * @email soda931vzr@163.com
 * @description
 */
public class BattercakeWithEggAndSausage extends BattercakeWithEgg {

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1根香肠";
    }

    // 加一根香肠2块钱
    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}