package com.gupao.pattern.strategy.pay;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:45
 * @email soda931vzr@163.com
 * @description
 */
public abstract class Payment {

    public abstract String getName();

    public abstract double queryBalance(String uid);

    public MsgResult pay(String uid, double amount) {
        if (queryBalance(uid) < amount) {
            return new MsgResult(500, "支付失败", "余额不足");
        } else {
            return new MsgResult(200, "支付成功", "扣款" + amount);
        }
    }
}