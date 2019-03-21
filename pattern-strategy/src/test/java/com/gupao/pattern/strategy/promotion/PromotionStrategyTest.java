package com.gupao.pattern.strategy.promotion;

import com.gupao.pattern.strategy.promotion.CouponStrategy;
import com.gupao.pattern.strategy.promotion.PromotionActivity;
import com.gupao.pattern.strategy.promotion.PromotionStrategy;
import com.gupao.pattern.strategy.promotion.PromotionStrategyFactory;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:39
 * @email soda931vzr@163.com
 * @description
 */
public class PromotionStrategyTest {

    public static void main(String[] args) {

        PromotionActivity activity = new PromotionActivity(new CouponStrategy());
        activity.execute();

//        PromotionStrategy strategy = PromotionStrategyFactory.getInstance(PromotionStrategyFactory.PromotionKey.CASHBACK);
//        strategy.doPromotion();
    }
}