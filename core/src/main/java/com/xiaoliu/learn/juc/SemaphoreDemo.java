package com.xiaoliu.learn.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore效果演示小程序
 *
 * @author Fubiao.Liu
 * @since 2020/11/9 16:15
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        int count = 3;
        int threadNum = 10;
        Semaphore semaphore = new Semaphore(count);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    semaphore.acquire();
                    System.out.println(name + "开始执行");
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.println(name + "结束执行");
                    System.out.println("----------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "线程" + i).start();
        }
    }
}
