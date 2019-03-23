package com.gupao.pattern.observer.classwork;

import com.google.common.eventbus.EventBus;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 17:41
 * @email soda931vzr@163.com
 * @description
 */
public class GuavaTeacherTest {

    public static void main(String[] args) {

        GuavaGPer gper = GuavaGPer.getInstance();
        GuavaTeacher teacher = new GuavaTeacher("tom");
        GuavaQuestion question = new GuavaQuestion();
        question.setContent("观察者模式和装饰模式有哪些应用场景？");
        question.setUsername("张三");
        question.setGuavaGPer(gper);
        EventBus eventBus = new EventBus();

        eventBus.register(teacher);
        eventBus.post(gper.publishQuestion(question));
    }
}