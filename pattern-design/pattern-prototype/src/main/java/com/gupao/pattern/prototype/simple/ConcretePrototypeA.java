package com.gupao.pattern.prototype.simple;

import java.util.List;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 19:35
 * @email soda931vzr@163.com
 * @description
 */
public class ConcretePrototypeA implements Prototype {

    private int id;
    private String name;
    private List hobbies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobbies() {
        return hobbies;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public Prototype clone() {

        ConcretePrototypeA prototypeA = new ConcretePrototypeA();

        prototypeA.setId(this.id);
        prototypeA.setName(this.name);
        prototypeA.setHobbies(this.hobbies);

        return prototypeA;
    }
}