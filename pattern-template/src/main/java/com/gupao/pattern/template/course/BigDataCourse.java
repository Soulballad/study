package com.gupao.pattern.template.course;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:02
 * @email soda931vzr@163.com
 * @description
 */
public class BigDataCourse extends NetworkCourse {

    private boolean needHomeworkFlag;

    public BigDataCourse(boolean needHomeworkFlag) {
        this.needHomeworkFlag = needHomeworkFlag;
    }

    @Override
    void checkHomework() {
        System.out.println("检查大数据课程课后作业");
    }

    @Override
    public boolean needHomework() {
        return this.needHomeworkFlag;
    }
}