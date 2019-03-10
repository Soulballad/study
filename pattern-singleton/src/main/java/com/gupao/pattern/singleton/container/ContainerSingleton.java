package com.gupao.pattern.singleton.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 15:04
 * @email soda931vzr@163.com
 * @description 容器式单例，利用map中key唯一来保存对象，实际上是线程不安全的
 */
public class ContainerSingleton {

    private ContainerSingleton() { }

    private static Map<String, Object> container = new ConcurrentHashMap<String, Object>();

    public static Object getInstance(String classNamme) {

        Object object = null;

        if (!container.containsKey(classNamme)) {

            try {
                object = Class.forName(classNamme).newInstance();
                container.put(classNamme, object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            object = container.get(classNamme);
        }

        return object;
    }
}