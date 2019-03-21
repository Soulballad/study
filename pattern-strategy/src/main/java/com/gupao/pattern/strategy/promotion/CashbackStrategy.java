package com.gupao.pattern.strategy.promotion;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:29
 * @email soda931vzr@163.com
 * @description
 */
public class CashbackStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {

        System.out.println("返现促销,返回的金额转到支付宝账号");
    }
}