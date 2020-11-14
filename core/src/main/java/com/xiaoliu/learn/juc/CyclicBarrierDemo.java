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
}
