package com.gupao.pattern.factory.abstractfactory;

/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:50
 * @email soda931vzr@163.com
 */
public interface ICarFactory {

    public IPoliceCar getPoliceCar();

    public IElectricCar getElectricCar();
}