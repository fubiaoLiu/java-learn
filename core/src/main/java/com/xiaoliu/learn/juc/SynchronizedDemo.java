package com.xiaoliu.learn.juc;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronized关键字效果演示小程序
 *
 * @author Fubiao.Liu
 * @since 2020/10/30 8:47
 **/
public class SynchronizedDemo {
    private static long num = 0;

    public static void main(String[] args) throws Exception {
        int threadNum = 100;
        int incrementNum = 10000;
        List<Thread> threads = new ArrayList<>(threadNum);
        Thread thread;
        for (int i = 0; i < threadNum; i++) {
            thread = new Thread(() -> {
                for (int j = 0; j < incrementNum; j++) {
                    increment();
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread runningThread : threads) {
            runningThread.join();
        }

        System.out.println("num:" + num);
    }

    private static synchronized void increment() {
        num++;
    }
}
