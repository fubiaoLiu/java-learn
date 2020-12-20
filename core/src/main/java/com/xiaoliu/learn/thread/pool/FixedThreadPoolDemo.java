package com.xiaoliu.learn.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定线程数量的线程池使用小程序
 * <p>
 * 问题：
 * 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 *
 * @author Fubiao.Liu
 * @since 2020/11/24 11:59
 **/
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        // 线程池运行状态rs
        // RUNNING      : 11100000000000000000000000000000
        // SHUTDOWN     : 00000000000000000000000000000000
        // STOP         : 00100000000000000000000000000000
        // TIDYING      : 01000000000000000000000000000000
        // TERMINATED   : 01100000000000000000000000000000
        // ctl          : 11100000000000000000000000000000（线程数为0时）
        // CAPACITY     : 00011111111111111111111111111111
        // 线程数wc（ctl的低29位）      : ctl & CAPACITY
        // 线程池运行状态rs（ctl的高3位）: ctl & ~CAPACITY
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(() -> {
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