package com.gupao.pattern.delegate.simple;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 22:24
 * @email soda931vzr@163.com
 * @description
 */
public class EmployeeB implements IEmployee{

    @Override
    public void work(String command) {

        System.out.println("员工B做" + command + "工作");
    }
}