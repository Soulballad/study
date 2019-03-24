package com.gupao.pattern.observer.classwork;

import com.gupao.pattern.observer.gperadvice.Question;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 16:08
 * @email soda931vzr@163.com
 * @description JDK提供的一种观察者的实现方式，被观察者
 */
public class GuavaGPer {

    private String name = "GPer生态圈";
    private static GuavaGPer gper = null;
    private GuavaGPer(){}

    public static GuavaGPer getInstance() {
        if (null == gper) {
            gper = new GuavaGPer();
        }
        return gper;
    }

    public String getNamme() {
        return name;
    }

    public GuavaQuestion publishQuestion(GuavaQuestion question) {
        System.out.println(question.getUsername()+"在"+this.name+"上提交了一个问题");
        return question;
    }
}