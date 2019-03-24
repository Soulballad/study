package com.gupao.pattern.proxy.dbroute;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:06
 * @email soda931vzr@163.com
 * @description
 */
public class OrderDao {

    public int insert(Order order) {

        System.out.println("OrderDao创建Order成功!");
        return 1;
    }
}