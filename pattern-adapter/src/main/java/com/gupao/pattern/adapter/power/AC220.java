package com.gupao.pattern.adapter.power;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:52
 * @email soda931vzr@163.com
 * @description
 */
public class AC220 {

    public int outputAC() {
        int output = 220;
        System.out.println("输出电压：" + output);
        return output;
    }
}