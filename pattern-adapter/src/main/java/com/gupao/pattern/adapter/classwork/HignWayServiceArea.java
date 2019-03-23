package com.gupao.pattern.adapter.classwork;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 12:17
 * @email soda931vzr@163.com
 * @description 高速服务区
 */
public class HignWayServiceArea extends GasStation {

    public void service(Car car) {
        if (car instanceof GasCar) {
            super.supplyGas(car);
        } else if (car instanceof ElectricCar) {
            this.supplyElectric(car);
        } else {
            throw new RuntimeException("无法提供服务");
        }
    }

    private void supplyElectric(Car car) {
        System.out.println("给[" + car.getName() + "]充电");
    }
}