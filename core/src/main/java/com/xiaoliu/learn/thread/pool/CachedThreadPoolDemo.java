package com.xiaoliu.learn.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不限制线程数量的线程池（如果没有空闲线程就创建线程）使用小程序
 * <p>
 * 问题：
 * 允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 *
 * @author Fubiao.Liu
 * @since 2020/11/24 11:59
 **/
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.submit(() -> {
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