package com.gupao.pattern.template.classwork;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 12:07
 * @email soda931vzr@163.com
 * @description
 */
public class StartCarTest {

    public static void main(String[] args) {

        StartCar startCar = new StartCar(new RightOperate());
        startCar.startProcess();
    }
}