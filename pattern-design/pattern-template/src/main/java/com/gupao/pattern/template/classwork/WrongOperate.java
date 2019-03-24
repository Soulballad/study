package com.gupao.pattern.template.classwork;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 12:05
 * @email soda931vzr@163.com
 * @description
 */
public class WrongOperate implements CarOperate {

    @Override
    public void dropHandBrake() {
        System.out.println("放手刹");
    }

    @Override
    public void changeGear() {
        System.out.println("换挡位");
    }

    @Override
    public void honk() {
        System.out.println("鸣喇叭");
    }

    @Override
    public void startEnginee() {
        //错误步骤：放手刹、鸣喇叭、换档位
        dropHandBrake();
        honk();
        changeGear();
        System.out.println("汽车错误启动");
    }
}