package com.gupao.pattern.strategy.pay.payport;

import com.gupao.pattern.strategy.pay.Payment;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:51
 * @email soda931vzr@163.com
 * @description
 */
public class JDPay extends Payment {

    @Override
    public String getName() {
        return "京东白条";
    }

    @Override
    public double queryBalance(String uid) {
        return 500;
    }
}