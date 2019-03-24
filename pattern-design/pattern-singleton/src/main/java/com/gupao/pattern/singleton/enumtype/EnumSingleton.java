package com.gupao.pattern.singleton.enumtype;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 14:31
 * @email soda931vzr@163.com
 * @description 枚举式单例
 */
public enum  EnumSingleton {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {

        return INSTANCE;
    }
}