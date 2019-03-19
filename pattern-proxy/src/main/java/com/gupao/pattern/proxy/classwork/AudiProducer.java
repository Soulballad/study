package com.gupao.pattern.proxy.classwork;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 21:51
 * @email soda931vzr@163.com
 * @description
 */
public class AudiProducer implements CarProducer {

    @Override
    public void saleCar() {

        System.out.println("销售奥迪系列汽车");
    }
}