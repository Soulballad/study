package com.gupao.pattern.delegate.simple;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 22:27
 * @email soda931vzr@163.com
 * @description
 */
public class Boss {

    public void command(String command, Leader leader) {

        leader.work(command);
    }
}