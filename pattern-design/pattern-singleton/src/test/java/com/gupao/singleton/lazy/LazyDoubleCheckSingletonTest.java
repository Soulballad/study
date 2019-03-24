package com.gupao.singleton.lazy;

import com.gupao.pattern.singleton.lazy.LazyDoubleCheckSingleton;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 11:25
 * @email soda931vzr@163.com
 * @description
 */
public class LazyDoubleCheckSingletonTest {

    public static void main(String[] args) {

        LazyDoubleCheckSingletonTest singletonTest = new LazyDoubleCheckSingletonTest();

        Thread thread1 = new Thread(singletonTest.new ExecutorThread());
        Thread thread2 = new Thread(singletonTest.new ExecutorThread());

        thread1.start();
        thread2.start();
    }

    class ExecutorThread implements Runnable {

        @Override
        public void run() {

            LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }
    }
}