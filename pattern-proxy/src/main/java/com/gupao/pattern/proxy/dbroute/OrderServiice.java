package com.gupao.pattern.proxy.dbroute;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:08
 * @email soda931vzr@163.com
 * @description
 */
public class OrderServiice implements IOrderService {

    private OrderDao orderDao;

    public OrderServiice() {

        orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {

        System.out.println("OrderService调用orderDao创建订单");
        return orderDao.insert(order);
    }
}