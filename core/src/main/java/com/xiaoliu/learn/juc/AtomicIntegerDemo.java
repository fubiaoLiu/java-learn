package com.xiaoliu.learn.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger同synchronzied使用小程序
 *
 * @author Fubiao.Liu
 * @since 2020/11/3 14:18
 **/
public class AtomicIntegerDemo {
    private int data = 0;
    private AtomicInteger atomicData = new AtomicInteger(0);

    public static void main(String[] args) {
        AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                atomicIntegerDemo.incrementSynchronized();
                atomicIntegerDemo.incrementAtomic();
            }).start();
        }
    }

    private synchronized void incrementSynchronized() {
        System.out.println("synchronzied:" + data++);
    }

    private void incrementAtomic() {
        System.out.println("atomic:" + atomicData.incrementAndGet());
    }
}
