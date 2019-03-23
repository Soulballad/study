package com.gupao.pattern.decorator.battercake.v2;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:55
 * @email soda931vzr@163.com
 * @description
 */
public class BattercakeDecoratorTest {

    public static void main(String[] args) {

        Battercake battercake;
        //买一个煎饼
        battercake = new BaseBattercake();
        //加一个鸡蛋
        battercake = new EggDecorator(battercake);
        //再加一个鸡蛋
        battercake = new EggDecorator(battercake);
        //在家根香肠
        battercake = new SausageDecorator(battercake);

        System.out.println(battercake.getMsg()+"，总价格：" + battercake.getPrice());
    }
}