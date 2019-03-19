package com.gupao.pattern.proxy.staticproxy.simple;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:02
 * @email soda931vzr@163.com
 * @description
 */
public class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        before();
        this.subject.request();
        after();
    }

    private void before() {

        System.out.println("called before request()");
    }

    private void after() {

        System.out.println("called after request()");
    }
}