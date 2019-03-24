package com.gupao.pattern.factory.simplefactory;

/**
 * @author Soulballad
 * @date 2019/3/7/0007 16:16
 * @email soda931vzr@163.com
 */
public class CarFactory {

    public ICar getCar1(String name) {

        if ("police".equals(name)) {
            return new PoliceCar();
        } else if ("electric".equals(name)) {
            return new ElectricCar();
        }
        return null;
    }

    public ICar getCar2(String className) {

        if (!(null == className || "".equals(className))) {

            try {
                return (ICar) Class.forName(className).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public ICar getCar3(Class<? extends ICar> clazz) {

        if (null != clazz) {

            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}