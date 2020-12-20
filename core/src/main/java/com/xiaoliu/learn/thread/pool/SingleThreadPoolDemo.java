package com.xiaoliu.learn.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程的线程池使用小程序
 * <p>
 *
 * @author Fubiao.Liu
 * @since 2020/11/24 11:59
 **/
public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            singleThreadPool.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] 任务被执行...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}