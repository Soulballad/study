package com.gupao.pattern.observer.gperadvice;

import java.util.Observable;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 16:08
 * @email soda931vzr@163.com
 * @description JDK提供的一种观察者的实现方式，被观察者
 */
public class GPer extends Observable {

    private String name = "GPer生态圈";
    private static GPer gper = null;
    private GPer(){}

    public static GPer getInstance() {
        if (null == gper) {
            gper = new GPer();
        }
        return gper;
    }

    public String getNamme() {
        return name;
    }

    public void publishQuestion(Question question) {
        System.out.println(question.getUsername()+"在"+this.name+"上提交了一个问题");
        setChanged();
        notifyObservers(question);
    }
}