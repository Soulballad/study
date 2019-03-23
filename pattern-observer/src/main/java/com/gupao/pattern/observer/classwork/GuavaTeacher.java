package com.gupao.pattern.observer.classwork;

import com.google.common.eventbus.Subscribe;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 16:13
 * @email soda931vzr@163.com
 * @description 观察者
 */
public class GuavaTeacher {

    private String name;

    public GuavaTeacher(String name) {
        this.name = name;
    }

    @Subscribe
    public void deal(GuavaQuestion question) {
        System.out.println("=================================");
        System.out.println(name + "老师，你好！\n" +
                "您收到一个来自“" + question.getGuavaGPer().getNamme() + "“的提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" +
                "提问者：" + question.getUsername());
    }
}