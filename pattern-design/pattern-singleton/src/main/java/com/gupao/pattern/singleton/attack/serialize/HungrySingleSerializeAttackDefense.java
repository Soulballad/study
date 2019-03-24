package com.gupao.pattern.singleton.attack.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 14:02
 * @email soda931vzr@163.com
 * @description 饿汉式防御序列化攻击，通过重写一个readResolve方法，直接返回一个实例，通过反序列化得到的对象会被该实例覆盖，从而保证单例
 */
public class HungrySingleSerializeAttackDefense implements Serializable {

    private static final HungrySingleSerializeAttackDefense INSTANCE = new HungrySingleSerializeAttackDefense();
    private HungrySingleSerializeAttackDefense(){}

    public static HungrySingleSerializeAttackDefense getInstance() {

        return INSTANCE;
    }

    public Object readResolve() {
        return INSTANCE;
    }

    public static void main(String[] args) {

        try {
            HungrySingleSerializeAttackDefense instance = HungrySingleSerializeAttackDefense.getInstance();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("HungrySingleSerializeAttackDefense.obj"));
            oos.writeObject(instance);
            oos.flush();
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("HungrySingleSerializeAttackDefense.obj"));
            HungrySingleSerializeAttackDefense singleton = (HungrySingleSerializeAttackDefense) ois.readObject();

            System.out.println(instance == singleton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}