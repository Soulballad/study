package com.gupao.pattern.factory.factorymethod;

/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:43
 * @email soda931vzr@163.com
 */
public class PoliceCarFactory implements ICarFactory {

    @Override
    public ICar getCar() {

        return new PoliceCar();
    }
}