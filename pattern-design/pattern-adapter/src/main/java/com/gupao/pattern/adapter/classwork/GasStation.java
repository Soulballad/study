package com.gupao.pattern.adapter.classwork;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 12:15
 * @email soda931vzr@163.com
 * @description 加油站
 */
public class GasStation {

    public void supplyGas(Car car) {
        System.out.println("给[" + car.getName() + "]加油");
    }
}