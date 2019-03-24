package com.gupao.pattern.decorator.battercake.v2;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:40
 * @email soda931vzr@163.com
 * @description
 */
public class BaseBattercake extends Battercake{

    protected String getMsg() {
        return "煎饼";
    }

    protected int getPrice() {
        return 5;
    }
}