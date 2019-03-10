package com.gupao.singleton.container;

import com.gupao.pattern.singleton.container.ContainerSingleton;
import com.gupao.pattern.singleton.executor.ConcurrentExecutor;

/**
 * @author Soulballad
 * @date 2019/3/10/0010 15:30
 * @email soda931vzr@163.com
 * @description
 */
public class ContainerSingletonTest {

    public static void main(String[] args) {

        try {
            ConcurrentExecutor.execute(new ConcurrentExecutor.RunHandler() {
                @Override
                public void handle() {
                    Object instance = ContainerSingleton.getInstance("com.gupao.singleton.container.TestClass");
                    System.out.println(System.currentTimeMillis()+":"+instance);
                }
            }, 10, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}