package com.gupao.pattern.singleton.enumtype;

import java.lang.reflect.Constructor;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 14:35
 * @email soda931vzr@163.com
 * @description 使用反射攻击枚举式单例，发现无法创建对象，因为jdk已经做了防御，所以枚举式单例不存在反射破坏的风险
 */
public class EnumSingletonReflectAttack {

    public static void main(String[] args) {

        try {

            Class<EnumSingleton> enumClass = EnumSingleton.class;

//            Constructor<EnumSingleton> constructor1 = enumClass.getDeclaredConstructor(null);
//            constructor1.setAccessible(true);
//            // java.lang.NoSuchMethodException: com.gupao.pattern.singleton.enumtype.EnumSingleton.<init>()
//            // 没有无参构造方法
//            EnumSingleton instance1 = constructor1.newInstance();

            Constructor<EnumSingleton> constructor2 = enumClass.getDeclaredConstructor(String.class, int.class);
            constructor2.setAccessible(true);
            // java.lang.IllegalArgumentException: Cannot reflectively create enum objects
            // 不能通过反射创建枚举对象
            EnumSingleton instance2 = constructor2.newInstance("yy", 1);
            System.out.println(instance2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}