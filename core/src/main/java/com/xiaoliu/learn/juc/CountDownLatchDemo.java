package com.xiaoliu.learn.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch效果演示小程序
 *
 * @author Fubiao.Liu
 * @since 2020/11/9 16:15
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "开始执行");
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.println(name + "结束执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里只会release 1，就是state减1
                countDownLatch.countDown();
            }, "线程" + i).start();
        }
        System.out.println("main线程等待其他线程执行完毕");
        // 如果有多个线程执行了await操作阻塞在这里，state等于0时会唤醒队列中阻塞的所有线程
        countDownLatch.await();
        System.out.println("其他线程执行完毕，main线程继续执行");
    }
}
