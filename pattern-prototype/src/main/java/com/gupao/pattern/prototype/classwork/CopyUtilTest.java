package com.gupao.pattern.prototype.classwork;

import java.util.Date;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 20:31
 * @email soda931vzr@163.com
 * @description
 */
public class CopyUtilTest {

    public static void main(String[] args) {

        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(15);
        person.setBirthday(new Date());
        person.setIdCard("123456");
        person.setAddress("Beijing");

        Person copy = (Person) CopyUtil.copy(person);

        System.out.println(person.toString());
        System.out.println(copy.toString());
    }
}