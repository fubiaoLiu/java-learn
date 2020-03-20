package com.xiaoliu.learn.thread;

/**
 * @description: 多线程交替打印数字字母
 * @author: FuBiaoLiu
 * @date: 2020/3/17
 */
public class AlternatePrinting {
    public static void main(String[] args) throws InterruptedException {
        char[] a1 = "ABCDEFG".toCharArray();
        char[] a2 = "1234567".toCharArray();
        final Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                while (true) {
                    for (char c : a1) {
                        System.out.print(c);
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                while (true) {
                    for (char c : a2) {
                        System.out.print(c);
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    o.notify();
                }
            }
        }).start();
    }
}
