package com.gupao.pattern.proxy.dbroute;

import com.gupao.pattern.proxy.dbroute.proxy.OrderServiceStaticProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:21
 * @email soda931vzr@163.com
 * @description
 */
public class DbRouteProxyTest {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws ParseException {

        IOrderService orderService = new OrderServiceStaticProxy(new OrderServiice());
//        orderService.createOrder(new Order("123",System.currentTimeMillis(),"ss"));
        orderService.createOrder(new Order("234",sdf.parse("2018-11-11 11:11:11").getTime(),"dd"));
    }
}