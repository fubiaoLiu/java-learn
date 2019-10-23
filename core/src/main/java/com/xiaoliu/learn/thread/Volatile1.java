package com.xiaoliu.learn.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description: volatile demo
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public class Volatile1 {
    static final int MAX = 50;
    static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (localValue != init_value) {
                    System.out.println("Reader:" + init_value);
                    localValue = init_value;
                }
            }
        }, "Reader").start();
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                System.out.println("Updater:" + (++localValue));
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }

}
