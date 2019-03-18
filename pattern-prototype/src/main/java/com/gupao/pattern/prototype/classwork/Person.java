package com.gupao.pattern.prototype.classwork;

import java.util.Date;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 20:20
 * @email soda931vzr@163.com
 * @description
 */
public class Person {

    private String name;
    private int age;
    private Date birthday;
    private String idCard;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}