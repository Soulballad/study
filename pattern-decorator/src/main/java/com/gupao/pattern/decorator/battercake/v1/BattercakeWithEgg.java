package com.gupao.pattern.decorator.battercake.v1;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:41
 * @email soda931vzr@163.com
 * @description
 */
public class BattercakeWithEgg extends Battercake {

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    @Override
    //加一个鸡蛋加1块钱
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}