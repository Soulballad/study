package com.gupao.pattern.decorator.battercake.v1;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 13:43
 * @email soda931vzr@163.com
 * @description
 */
public class BattercakeTest {

    public static void main(String[] args) {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getMsg() + "，总价格：" + battercake.getPrice());

        Battercake battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getMsg() + "，总价格：" + battercakeWithEgg.getPrice());

        Battercake battercakeWithEggAndSausage = new BattercakeWithEggAndSausage();
        System.out.println(battercakeWithEggAndSausage.getMsg() + "，总价格：" + battercakeWithEggAndSausage.getPrice());

    }
}