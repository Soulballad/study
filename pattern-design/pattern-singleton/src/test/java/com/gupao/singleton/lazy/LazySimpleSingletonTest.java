package com.gupao.singleton.lazy;

import com.gupao.pattern.singleton.lazy.LazySimpleSingleton;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 11:10
 * @email soda931vzr@163.com
 * @description
 */
public class LazySimpleSingletonTest {

    public static void main(String[] args) {

        LazySimpleSingletonTest singletonTest = new LazySimpleSingletonTest();

        Thread thread1 = new Thread(singletonTest.new ExecutorThread());
        Thread thread2 = new Thread(singletonTest.new ExecutorThread());

        thread1.start();
        thread2.start();
    }

    class ExecutorThread implements Runnable {

        @Override
        public void run() {

            LazySimpleSingleton instance = LazySimpleSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }
    }
}