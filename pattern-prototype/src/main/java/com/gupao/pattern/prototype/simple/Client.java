package com.gupao.pattern.prototype.simple;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 19:38
 * @email soda931vzr@163.com
 * @description
 */
public class Client {

    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public Prototype startClone(Prototype basePrototype) {

        return basePrototype.clone();
    }
}