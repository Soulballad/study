package com.gupao.pattern.observer.gperadvice;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 16:13
 * @email soda931vzr@163.com
 * @description 观察者
 */
public class Teacher implements Observer {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        GPer gper = (GPer) o;
        Question question = (Question) arg;
        System.out.println("=================================");
        System.out.println(name + "老师，你好！\n" +
                "您收到一个来自“" + gper.getNamme() + "“的提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" +
                "提问者：" + question.getUsername());
    }
}