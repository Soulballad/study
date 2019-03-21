package com.gupao.pattern.strategy.pay.payport;

import com.gupao.pattern.strategy.pay.Payment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:53
 * @email soda931vzr@163.com
 * @description
 */
public class PayStrategy {

    public static final String ALI_PAY = "alipay";
    public static final String JD_PAY = "jdpay";
    public static final String WECHAT_PAY = "wechatpay";
    public static final String UNION_PAY = "unionpay";
    public static final String DEFAULT_PAY = ALI_PAY;

    private PayStrategy(){}

    private static final Map<String, Payment> pay_map = new HashMap<>();

    static {
        pay_map.put(ALI_PAY, new AliPay());
        pay_map.put(JD_PAY, new JDPay());
        pay_map.put(WECHAT_PAY, new WechatPay());
        pay_map.put(UNION_PAY, new UnionPay());
    }

    public static Payment get(String payKey) {
        if (!pay_map.containsKey(payKey)) {
            return pay_map.get(DEFAULT_PAY);
        }
        return pay_map.get(payKey);
    }
}