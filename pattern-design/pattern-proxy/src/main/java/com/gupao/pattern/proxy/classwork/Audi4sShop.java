package com.gupao.pattern.proxy.classwork;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 21:52
 * @email soda931vzr@163.com
 * @description
 */
public class Audi4sShop implements CarProducer {

    private AudiProducer audiProducer;

    public Audi4sShop(AudiProducer audiProducer) {
        this.audiProducer = audiProducer;
    }

    @Override
    public void saleCar() {

        introduce();
        this.audiProducer.saleCar();
        keep();
    }

    private void introduce() {

        System.out.println("介绍奥迪汽车");
    }

    private void keep() {

        System.out.println("保养奥迪汽车");
    }
}