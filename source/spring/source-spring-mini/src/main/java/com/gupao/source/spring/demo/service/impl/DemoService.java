package com.gupao.source.spring.demo.service.impl;

import com.gupao.source.spring.mvcframework.annotation.GPService;
import com.gupao.source.spring.demo.service.IDemoService;

/**
 * @author Soulballad
 * @date 2019/3/25/0025 20:29
 * @email soda931vzr@163.com
 * @description
 */
@GPService
public class DemoService implements IDemoService {

    @Override
    public String get(String name) {
        return "my name is " + name;
    }
}