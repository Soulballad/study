package com.gupao.pattern.prototype.classwork;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 20:22
 * @email soda931vzr@163.com
 * @description
 */
public class CopyUtil {

    public static Object copy(Object object) {

        try {
            Class<?> clazz = object.getClass();
            Constructor<?> constructor = clazz.getDeclaredConstructor(null);
            constructor.setAccessible(true);

            Object copyObj = constructor.newInstance();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(copyObj, field.get(object));
            }

            return copyObj;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}