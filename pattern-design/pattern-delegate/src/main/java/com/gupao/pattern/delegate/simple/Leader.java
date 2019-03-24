package com.gupao.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/3/19/0019 22:25
 * @email soda931vzr@163.com
 * @description
 */
public class Leader implements IEmployee {

    private Map<String, IEmployee> map = new HashMap<>();

    public Leader() {

        map.put("登录", new EmployeeA());
        map.put("加密", new EmployeeB());
    }

    @Override
    public void work(String command) {

        map.get(command).work(command);
    }
}