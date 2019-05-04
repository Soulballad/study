package com.gupao.source.spring.orm.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author Soulballad
 * @date 2019/5/3 16:30
 * @email soda931vzr@163.com
 * @description
 */
@Table(name="t_order")
@Data
public class Order {

    private Long id;
    @Column(name="mid")
    private Long memberId;
    private String detail;
    private Long createTime;
    private String createTimeFmt;
}
