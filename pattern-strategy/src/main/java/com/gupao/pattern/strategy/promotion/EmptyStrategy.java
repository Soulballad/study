package com.gupao.pattern.strategy.promotion;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:30
 * @email soda931vzr@163.com
 * @description
 */
public class EmptyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("无优惠");
    }
}