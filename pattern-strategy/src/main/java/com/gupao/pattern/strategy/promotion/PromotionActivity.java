package com.gupao.pattern.strategy.promotion;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:31
 * @email soda931vzr@163.com
 * @description
 */
public class PromotionActivity {

    private PromotionStrategy strategy;

    public PromotionActivity(PromotionStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {

        this.strategy.doPromotion();
    }
}