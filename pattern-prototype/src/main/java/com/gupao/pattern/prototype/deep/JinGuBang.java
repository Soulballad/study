package com.gupao.pattern.prototype.deep;

import java.io.Serializable;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 19:47
 * @email soda931vzr@163.com
 * @description
 */
public class JinGuBang implements Serializable {

    public float h = 100;
    public float d = 10;

    public void big() {

        this.h *= 2;
        this.d *= 2;
    }

    public void small() {

        this.h /= 2;
        this.d /= 2;
    }
}