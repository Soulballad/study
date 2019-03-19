package com.gupao.pattern.proxy.classwork;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 21:56
 * @email soda931vzr@163.com
 * @description
 */
public class Audi4sShopTest {

    public static void main(String[] args) {

        CarProducer producer = new Audi4sShop(new AudiProducer());
        producer.saleCar();
    }
}