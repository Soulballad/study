package com.gupao.pattern.delegate.simple;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 22:23
 * @email soda931vzr@163.com
 * @description
 */
public class EmployeeA implements IEmployee {

    @Override
    public void work(String command) {

        System.out.println("员工A做" + command + "工作");
    }
}