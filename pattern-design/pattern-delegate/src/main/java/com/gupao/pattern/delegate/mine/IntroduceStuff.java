package com.gupao.pattern.delegate.mine;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 20:58
 * @email soda931vzr@163.com
 * @description
 */
public class IntroduceStuff implements IStuff {

    @Override
    public void work() {

        System.out.println("向客户介绍汽车");
    }
}