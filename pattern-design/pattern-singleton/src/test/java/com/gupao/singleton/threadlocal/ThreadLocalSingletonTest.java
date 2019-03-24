package com.gupao.singleton.threadlocal;

import com.gupao.pattern.singleton.threadlocal.ThreadLocalSingleton;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 15:24
 * @email soda931vzr@163.com
 * @description
 */
public class ThreadLocalSingletonTest {

    class ExecutorThread implements Runnable {
        @Override
        public void run() {
            ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }
    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());

        ThreadLocalSingletonTest singletonTest = new ThreadLocalSingletonTest();

        Thread thread1 = new Thread(singletonTest.new ExecutorThread());
        Thread thread2 = new Thread(singletonTest.new ExecutorThread());

        thread1.start();
        thread2.start();
    }
}