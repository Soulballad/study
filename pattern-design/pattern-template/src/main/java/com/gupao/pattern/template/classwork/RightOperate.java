package com.gupao.pattern.template.classwork;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 12:03
 * @email soda931vzr@163.com
 * @description
 */
public class RightOperate implements CarOperate {

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
        //正确步骤：换档位，鸣喇叭，放手刹
        changeGear();
        honk();
        dropHandBrake();
        System.out.println("汽车正确启动");
    }
}