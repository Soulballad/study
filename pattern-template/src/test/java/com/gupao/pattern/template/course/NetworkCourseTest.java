package com.gupao.pattern.template.course;

/**
 * @author Soulballad
 * @date 2019/3/22/0022 21:04
 * @email soda931vzr@163.com
 * @description
 */
public class NetworkCourseTest {

    public static void main(String[] args) {

        System.out.println("---java课程---");
        NetworkCourse javaCourse = new JavaCourse();
        javaCourse.createCourse();

        System.out.println("---大数据课程---");
        NetworkCourse bigDataCourse = new BigDataCourse(true);
        bigDataCourse.createCourse();
    }
}