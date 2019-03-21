package com.gupao.pattern.delegate.mine;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 20:59
 * @email soda931vzr@163.com
 * @description
 */
public class PainterStuff implements IStuff {

    @Override
    public void work() {

        System.out.println("给汽车喷漆");
    }
}