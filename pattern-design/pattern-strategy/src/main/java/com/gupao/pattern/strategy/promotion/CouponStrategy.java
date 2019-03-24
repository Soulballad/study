package com.gupao.pattern.strategy.promotion;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:30
 * @email soda931vzr@163.com
 * @description
 */
public class CouponStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("领取优惠券,课程的价格直接减优惠券面值抵扣");
    }
}