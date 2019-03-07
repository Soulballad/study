package com.gupao.pattern.factory.abstractfactory;

/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:54
 * @email soda931vzr@163.com
 */
public class AudiElectricCar implements IElectricCar {

    @Override
    public void charge() {

        System.out.println("audi...charge...");
    }
}