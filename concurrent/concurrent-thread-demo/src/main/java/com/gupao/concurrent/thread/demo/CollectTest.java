package com.gupao.concurrent.thread.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Soulballad
 * @date 2019/5/12 16:36
 * @email soda931vzr@163.com
 * @description
 */
public class CollectTest {

    public static void main(String[] args) {

        String threadName = "---thread---";

        List<Future> resultList = new ArrayList<Future>();
        ExecutorService executors = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            Future<?> future = executors.submit(new CollectThread(threadName + i));
            resultList.add(future);
        }
    }
}
