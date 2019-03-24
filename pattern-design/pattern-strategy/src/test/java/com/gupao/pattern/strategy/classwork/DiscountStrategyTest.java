package com.gupao.pattern.strategy.classwork;

import java.util.Date;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 22:25
 * @email soda931vzr@163.com
 * @description
 */
public class DiscountStrategyTest {

    public static void main(String[] args) {

        DiscountActivity discountActivity = DiscountStrategy.get(new Date());
        System.out.println(discountActivity.discount(100));;
    }
}