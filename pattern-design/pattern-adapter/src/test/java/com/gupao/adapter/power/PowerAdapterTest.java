package com.gupao.adapter.power;

import com.gupao.pattern.adapter.power.AC220;
import com.gupao.pattern.adapter.power.DC5;
import com.gupao.pattern.adapter.power.PowerAdapter;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:56
 * @email soda931vzr@163.com
 * @description
 */
public class PowerAdapterTest {

    public static void main(String[] args) {

        DC5 dc = new PowerAdapter(new AC220());
        dc.outputDC();
    }
}