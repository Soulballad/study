package com.gupao.concurrent.thread.demo;

/**
 * @author Soulballad
 * @date 2019/5/12 16:36
 * @email soda931vzr@163.com
 * @description
 */
public class CollectThread implements Runnable {

    private String name;

    public CollectThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        //处理一些业务逻辑
        System.out.println(name);
    }
}
