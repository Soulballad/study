package com.gupao.pattern.template.classwork;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 11:47
 * @email soda931vzr@163.com
 * @description 根据科目三起步流程设计
 */
public class StartCar {

    private CarOperate operate;

    public StartCar(CarOperate operate) {
        this.operate = operate;
    }

    public void startProcess() {

        //1、绕车一周
        walkAroundCar();

        //2、开车门
        openDoor();

        //3、系安全带
        fastenBelt();

        //4、启动
        operate.startEnginee();
    }

    private final void walkAroundCar() {
        System.out.println("绕车一周，观察周围");
    }

    private final void fastenBelt() {
        System.out.println("系安全带");
    }

    private final void openDoor() {
        System.out.println("开车门");
    }
}