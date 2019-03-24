package com.gupao.pattern.factory.simplefactory;

/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:13
 * @email soda931vzr@163.com
 */
public class ElectricCar implements ICar {
    @Override
    public void produce() {
        System.out.println("生产电子汽车");
    }
}