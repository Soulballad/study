package com.gupao.pattern.strategy.pay;

import com.gupao.pattern.strategy.pay.payport.PayStrategy;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 22:01
 * @email soda931vzr@163.com
 * @description
 */
public class PayStrategyTest {

    public static void main(String[] args) {

        Order order = new Order("zhangsan", "123456", 666);
        System.out.println(order.pay(PayStrategy.WECHAT_PAY));;
    }
}