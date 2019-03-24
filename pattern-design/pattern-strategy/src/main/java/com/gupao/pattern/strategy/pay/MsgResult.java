package com.gupao.pattern.strategy.pay;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:49
 * @email soda931vzr@163.com
 * @description
 */
public class MsgResult {

    private int code;
    private String data;
    private String msg;

    public MsgResult(int code, String msg, String data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public String toString(){
        return ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }
}