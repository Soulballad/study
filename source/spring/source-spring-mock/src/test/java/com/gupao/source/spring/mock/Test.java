package com.gupao.source.spring.mock;

import com.gupao.source.spring.mock.demo.action.MyAction;
import com.gupao.source.spring.mock.springfamework.context.GPApplicationContext;

/**
 * @author Soulballad
 * @date 2019/4/22 21:08
 * @email soda931vzr@163.com
 * @description
 */
public class Test {

    @org.junit.Test
    public void test() throws Exception {

        GPApplicationContext applicationContext = new GPApplicationContext("classpath:applicationContext.properties");
//        System.out.println(applicationContext.getBean("DemoAction"));
        System.out.println(applicationContext.getBean(MyAction.class));
    }
}
