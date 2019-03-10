package com.gupao.pattern.singleton.attack.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 12:06
 * @email soda931vzr@163.com
 * @description 饿汉式防御反射攻击
 */
public class HungrySimpleSingletonAttackDefense {

    private static boolean flag = false;
    private final static HungrySimpleSingletonAttackDefense singleton = new HungrySimpleSingletonAttackDefense();

    private HungrySimpleSingletonAttackDefense() {

        synchronized (HungrySimpleSingletonAttackDefense.class) {

            if (singleton != null) {

                throw new RuntimeException("不能通过反射创建单例！");
            }
        }
    }

    public static HungrySimpleSingletonAttackDefense getInstance() {

        return singleton;
    }

    public static void main(String[] args) {

        try {
            Class<HungrySimpleSingletonAttackDefense> attackDefenseClass = HungrySimpleSingletonAttackDefense.class;

            Constructor<HungrySimpleSingletonAttackDefense> constructor = attackDefenseClass.getDeclaredConstructor(null);
            constructor.setAccessible(true);
//            Field field = attackDefenseClass.getDeclaredField("flag");
//            field.setAccessible(true);
//            field.setBoolean(HungrySimpleSingletonAttackDefense.class, false);

            HungrySimpleSingletonAttackDefense instance1 = constructor.newInstance();

            System.out.println(instance1);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}