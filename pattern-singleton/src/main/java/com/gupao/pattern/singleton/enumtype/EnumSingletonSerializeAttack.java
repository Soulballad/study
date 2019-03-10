package com.gupao.pattern.singleton.enumtype;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 14:43
 * @email soda931vzr@163.com
 * @description 使用序列化攻击枚举式单例，发现没有风险
 */
public class EnumSingletonSerializeAttack {

    public static void main(String[] args) {

        EnumSingleton instance = EnumSingleton.INSTANCE;
        instance.setData(new Object());

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EnumSingleton.obj"));
            oos.writeObject(instance);
            oos.flush();
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EnumSingleton.obj"));
            EnumSingleton singleton = (EnumSingleton) ois.readObject();

            System.out.println(instance.getData() == singleton.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}