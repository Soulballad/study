package com.gupao.source.spring.mock.springfamework.context;

/**
 * @author Soulballad
 * @date 2019/4/21 16:40
 * @email soda931vzr@163.com
 * @description 通过解耦的方式获取IOC容器的顶层设计
 */
public interface GPApplicationContxtAware {

    void setApplicationContext(GPApplicationContext applicationContext);
}
