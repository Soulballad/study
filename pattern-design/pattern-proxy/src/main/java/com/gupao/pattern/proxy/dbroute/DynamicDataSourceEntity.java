package com.gupao.pattern.proxy.dbroute;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:11
 * @email soda931vzr@163.com
 * @description
 */
public class DynamicDataSourceEntity {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    private static final String DEFAULT_SOURCE = null;

    public static String get() {
        return local.get();
    }

    public static void set(String source) {
        local.set(source);
    }

    public static void set(int year) {
        local.set("DB_" + year);
    }

    public static void restore() {
        local.set(DEFAULT_SOURCE);
    }
}