package com.gupao.pattern.strategy.pay.payport;

import com.gupao.pattern.strategy.pay.Payment;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:52
 * @email soda931vzr@163.com
 * @description
 */
public class UnionPay extends Payment {

    @Override
    public String getName() {
        return "银联支付";
    }

    @Override
    public double queryBalance(String uid) {
        return 128;
    }
}