package com.gupao.singleton.lazy;

import com.gupao.pattern.singleton.lazy.LazySimpleSynchronizedSingleton;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 11:14
 * @email soda931vzr@163.com
 * @description
 */
public class LazySimpleSynchronizedSingletonTest {

    public static void main(String[] args) {

        LazySimpleSynchronizedSingletonTest singletonTest = new LazySimpleSynchronizedSingletonTest();

        Thread thread1 = new Thread(singletonTest.new ExecutorThread());
        Thread thread2 = new Thread(singletonTest.new ExecutorThread());

        thread1.start();
        thread2.start();
    }

    class ExecutorThread implements Runnable {

        @Override
        public void run() {

            LazySimpleSynchronizedSingleton instance = LazySimpleSynchronizedSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }
    }
}