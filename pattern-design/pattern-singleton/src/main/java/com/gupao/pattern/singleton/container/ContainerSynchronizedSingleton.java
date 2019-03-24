package com.gupao.pattern.singleton.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 15:04
 * @email soda931vzr@163.com
 * @description 容器式单例，使用Synchronized关键字使线程安全
 */
public class ContainerSynchronizedSingleton {

    private ContainerSynchronizedSingleton() { }

    private static Map<String, Object> container = new ConcurrentHashMap<String, Object>();

    public static Object getInstance(String classNamme) {

        Object object = null;

        synchronized (container) {

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
        }

        return object;
    }
}