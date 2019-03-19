package com.gupao.pattern.proxy.staticproxy.example;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 20:52
 * @email soda931vzr@163.com
 * @description
 */
public class Father implements Person {

    private Son son;

    public Father(Son son) {
        this.son = son;
    }

    @Override
    public void findLove() {
        System.out.println("父亲物色对象");
        this.son.findLove();
        System.out.println("双方父母同意，确立关系");
    }
}