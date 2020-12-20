package com.xiaoliu.learn.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 调度线程池使用小程序
 * <p>
 *
 * @author Fubiao.Liu
 * @since 2020/11/24 11:59
 **/
public class ScheduleThreadPoolDemo {
    public static void main(String[] args) {
        method1();
        // method2();
    }

    private static void method1() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.schedule(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] 任务被执行...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i, TimeUnit.SECONDS);
        }
    }

    /**
     * 不会创建非核心线程，即使同时有超过核心线程数的任务提交
     */
    private static void method2() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.schedule(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] 任务被执行...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 0, TimeUnit.SECONDS);
        }
    }
}