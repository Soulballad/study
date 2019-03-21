package com.gupao.pattern.strategy.promotion;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:31
 * @email soda931vzr@163.com
 * @description
 */
public class GroupbuyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("拼团，满20人成团，全团享受团购价");
    }
}