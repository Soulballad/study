package com.gupao.pattern.strategy.classwork;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 22:14
 * @email soda931vzr@163.com
 * @description
 */
public class SpringDiscount implements DiscountActivity {

    @Override
    public double discount(double amount) {

        return amount * 0.95;
    }
}