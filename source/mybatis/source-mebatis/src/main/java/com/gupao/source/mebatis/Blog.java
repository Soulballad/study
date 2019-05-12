package com.gupao.source.mebatis;

/**
 * @author Soulballad
 * @date 2019/5/7 22:05
 * @email soda931vzr@163.com
 * @description
 */
public class Blog {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
