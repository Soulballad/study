package com.gupao.pattern.factory.simplefactory;

/**
 * @author yangpeng
 * @date 2019/3/7/0007 16:12
 * @email soda931vzr@163.com
 */
public class PoliceCar implements ICar{

    @Override
    public void produce() {
        System.out.println("生产警用汽车");
    }
}