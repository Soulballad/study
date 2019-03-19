package com.gupao.pattern.proxy.dbroute;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:05
 * @email soda931vzr@163.com
 * @description
 */
public class Order {

    private String id;
    private Long createTime;
    private Object orderInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Object getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Order(String id, Long createTime, Object orderInfo) {
        this.id = id;
        this.createTime = createTime;
        this.orderInfo = orderInfo;
    }
}