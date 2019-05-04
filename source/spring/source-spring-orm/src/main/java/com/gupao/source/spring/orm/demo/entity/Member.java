package com.gupao.source.spring.orm.demo.entity;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Soulballad
 * @date 2019/5/3 16:18
 * @email soda931vzr@163.com
 * @description
 */
@Table(name="t_member")
@Data
public class Member implements Serializable {
   private Long id;
   private String name;
   private String addr;
   private Integer age;
}
