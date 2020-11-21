package com.xiaoliu.learn.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier效果演示小程序
 *
 * @author Fubiao.Liu
 * @since 2020/11/12 17:15
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // method1();
        method2();
    }

    /**
     * 线程执行await会进入队列阻塞等待
     * 当达到指定数量的线程都执行了await，那么会唤醒队列中所有的线程，让他们继续执行
     */
    private static void method1() {
        int count = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.println(name + "已就位...");
                    cyclicBarrier.await();
                    System.out.println(name + "出发...");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "线程" + i).start();
        }
    }

    /**
     * 线程执行await会进入队列阻塞等待
     * 当达到指定数量的线程都执行了await，最后一个执行await的线程会先执行CyclicBarrier初始化时指定的Runnable的run方法（这个是直接执行的run方法）
     * 然后再唤醒队列中所有的线程，让他们继续执行
     */
    private static void method2() {
        int count = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println(count + "个线程执行完毕，合并结果......");
        });
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.println(name + "已执行完成...");
                    cyclicBarrier.await();
                    System.out.println(name + "执行结束，退出...");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "线程" + i).start();
        }
    }
}
