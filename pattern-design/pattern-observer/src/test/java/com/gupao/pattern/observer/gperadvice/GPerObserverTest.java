package com.gupao.pattern.observer.gperadvice;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 16:18
 * @email soda931vzr@163.com
 * @description
 */
public class GPerObserverTest {

    public static void main(String[] args) {

        GPer gper = GPer.getInstance();
        Teacher tom = new Teacher("tom");

        Question question = new Question();
        question.setUsername("小明");
        question.setContent("观察者模式有哪些使用场景？");
        gper.addObserver(tom);
        gper.publishQuestion(question);
    }
}