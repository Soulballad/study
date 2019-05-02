package com.gupao.source.spring.mock.springfamework.aop.config;

import lombok.Data;

/**
 * @author Soulballad
 * @date 2019/5/2 13:31
 * @email soda931vzr@163.com
 * @description
 */
@Data
public class GPAopConfig {

    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;
}
