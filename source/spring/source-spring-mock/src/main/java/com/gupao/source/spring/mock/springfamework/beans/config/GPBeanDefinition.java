package com.gupao.source.spring.mock.springfamework.beans.config;

import lombok.Data;

/**
 * @author Soulballad
 * @date 2019/4/21 16:33
 * @email soda931vzr@163.com
 * @description
 */
@Data
public class GPBeanDefinition {

    private String beanClassName;
    private boolean isLazyInit = false;
    private String factoryBeanName;
    private boolean isSingleton = true;
}
