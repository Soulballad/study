package com.gupao.pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:33
 * @email soda931vzr@163.com
 * @description
 */
public class PromotionStrategyFactory {

    private static final Map<String, PromotionStrategy> strategy_map = new HashMap<>();

    static {

        strategy_map.put(PromotionKey.COUPON, new CouponStrategy());
        strategy_map.put(PromotionKey.CASHBACK, new CashbackStrategy());
        strategy_map.put(PromotionKey.GROUPBUY, new GroupbuyStrategy());
    }

    private PromotionStrategyFactory(){}

    private static final PromotionStrategy NON_PROMOTION = new EmptyStrategy();

    public static PromotionStrategy getInstance(String key) {

        PromotionStrategy strategy = strategy_map.get(key);
        return null == strategy ? NON_PROMOTION : strategy;
    }

    public interface PromotionKey{

        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
        String COUPON = "COUPON";
    }
}