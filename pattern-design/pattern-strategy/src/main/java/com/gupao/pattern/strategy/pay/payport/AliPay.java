package com.gupao.pattern.strategy.pay.payport;

import com.gupao.pattern.strategy.pay.Payment;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:51
 * @email soda931vzr@163.com
 * @description
 */
public class AliPay extends Payment {

    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    public double queryBalance(String uid) {
        return 900;
    }
}