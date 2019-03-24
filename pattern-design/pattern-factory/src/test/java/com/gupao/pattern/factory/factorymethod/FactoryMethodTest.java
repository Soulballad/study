package com.gupao.pattern.factory.factorymethod;

/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:46
 * @email soda931vzr@163.com
 */
public class FactoryMethodTest {

    public static void main(String[] args) {

        ICarFactory policeCarFactory = new PoliceCarFactory();
        policeCarFactory.getCar().produce();

        ElectricCarFactory electricCarFactory = new ElectricCarFactory();
        electricCarFactory.getCar().produce();
    }
}