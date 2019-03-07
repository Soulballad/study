package com.gupao.pattern.factory.simplefactory;


/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:18
 * @email soda931vzr@163.com
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {

        CarFactory factory = new CarFactory();

        ICar car11 = factory.getCar1("police");
        car11.produce();
        ICar car12 = factory.getCar1("electric");
        car12.produce();

        ICar car21 = factory.getCar2("com.gupao.pattern.factory.simplefactory.PoliceCar");
        car21.produce();
        ICar car22 = factory.getCar2("com.gupao.pattern.factory.simplefactory.ElectricCar");
        car22.produce();

        ICar car31 = factory.getCar3(PoliceCar.class);
        car31.produce();
        ICar car32 = factory.getCar3(ElectricCar.class);
        car32.produce();
    }
}