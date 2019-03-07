package com.gupao.pattern.factory.abstractfactory;

/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:57
 * @email soda931vzr@163.com
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {

        AudiCarFactory audiCarFactory = new AudiCarFactory();
        audiCarFactory.getPoliceCar().ring();
        audiCarFactory.getElectricCar().charge();

        BenzCarFactory benzCarFactory = new BenzCarFactory();
        benzCarFactory.getPoliceCar().ring();
        benzCarFactory.getElectricCar().charge();
    }
}