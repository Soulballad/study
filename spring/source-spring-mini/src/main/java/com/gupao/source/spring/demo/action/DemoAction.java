package com.gupao.source.spring.demo.action;

import com.gupao.source.spring.mvcframework.annotation.GPAutowired;
import com.gupao.source.spring.mvcframework.annotation.GPController;
import com.gupao.source.spring.mvcframework.annotation.GPRequestMapping;
import com.gupao.source.spring.mvcframework.annotation.GPRequestParam;
import com.gupao.source.spring.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Soulballad
 * @date 2019/3/25/0025 20:26
 * @email soda931vzr@163.com
 * @description
 */
@GPRequestMapping("/demo")
@GPController
public class DemoAction {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @GPRequestParam("name") String name) throws IOException {
        String value = this.demoService.get(name);
        response.getWriter().write(value);
    }

    @GPRequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, @GPRequestParam("a") Integer a, @GPRequestParam("b") Integer b) throws IOException {

        String value = "a + b = " + (a + b);
        response.getWriter().write(value);
    }
}