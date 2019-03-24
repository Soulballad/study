package com.gupao.pattern.template.course;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 20:56
 * @email soda931vzr@163.com
 * @description
 */
public abstract class NetworkCourse {

    protected final void createCourse() {

        //1、发布预习资料
        this.postPreResource();

        //2、制作PPT
        this.createPPT();

        //3、在线直播
        this.liveVideo();

        //4、提交课件、课堂笔记
        this.postNote();

        //5、发布源码
        this.postSource();

        //6、布置作业，有些课是没有作业的
        //如果有作业的话，检查作业，如果没有作业，就完成了
        if (needHomework()) {
            checkHomework();
        }
    }

    abstract void checkHomework();

    public boolean needHomework() {
        return false;
    }

    private final void postSource() {
        System.out.println("发布源码");
    }

    private final void postNote() {
        System.out.println("提交课件和笔记");
    }

    private final void liveVideo() {
        System.out.println("在线直播");
    }

    private final void createPPT() {

        System.out.println("创建备课PPT");
    }

    private final void postPreResource() {

        System.out.println("发布预习资料");
    }
}