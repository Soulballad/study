package com.gupao.pattern.proxy.staticproxy.simple;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:03
 * @email soda931vzr@163.com
 * @description
 */
public class Client {

    public static void main(String[] args) {

        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }
}