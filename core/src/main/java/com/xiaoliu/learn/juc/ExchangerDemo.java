package com.xiaoliu.learn.juc;

import java.util.concurrent.Exchanger;

/**
 * Exchanger效果演示小程序
 * 两个线程进行数据交换的工具
 *
 * @author Fubiao.Liu
 * @since 2020/11/18 19:15
 **/
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                String thread2Data = exchanger.exchange("线程1的数据");
                System.out.println("[" + Thread.currentThread().getName() + "]从线程2交换的数据:" + thread2Data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            try {
                String thread1Data = exchanger.exchange("线程2的数据");
                System.out.println("[" + Thread.currentThread().getName() + "]从线程1交换的数据:" + thread1Data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }
}