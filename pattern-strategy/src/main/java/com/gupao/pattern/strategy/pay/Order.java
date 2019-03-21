package com.gupao.pattern.strategy.pay;

import com.gupao.pattern.strategy.pay.payport.PayStrategy;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:58
 * @email soda931vzr@163.com
 * @description
 */
public class Order {

    private String uid;
    private String id;
    private double amount;

    public Order(String uid, String id, double amount) {
        this.uid = uid;
        this.id = id;
        this.amount = amount;
    }

    public MsgResult pay() {

        return pay(PayStrategy.DEFAULT_PAY);
    }

    public MsgResult pay(String payKey) {

        Payment payment = PayStrategy.get(payKey);
        System.out.println("欢迎使用" + payment.getName());
        System.out.println("本次交易金额为：" + amount + "，开始扣款...");
        return payment.pay(uid, amount);
    }
}