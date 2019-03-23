package com.gupao.pattern.adapter.power;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:53
 * @email soda931vzr@163.com
 * @description
 */
public class PowerAdapter implements DC5 {

    private AC220 ac220;

    public PowerAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int outputDC() {
        int adapterInput = ac220.outputAC();
        int adapterOutput = adapterInput/ 44;
        System.out.println("使用PowerAdapter输入AC:" + adapterInput + "V,输出DC：" + adapterOutput + "V");

        return adapterOutput;
    }
}