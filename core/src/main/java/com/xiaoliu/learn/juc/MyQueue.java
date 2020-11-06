package com.xiaoliu.learn.juc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 简易内存队列使用wait notify
 *
 * @author Fubiao.Liu
 * @since 2020/11/3 8:58
 **/
public class MyQueue {
    private static final int MAX_SIZE = 10;
    private Queue<Integer> queue = new LinkedList<>();

    public synchronized void offer(Integer e) throws InterruptedException {
        while (queue.size() >= MAX_SIZE) {
            wait();
        }
        queue.offer(e);
        notifyAll();
    }

    public synchronized Integer take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Integer e = queue.poll();
        notifyAll();
        return e;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    myQueue.offer(i);
                    System.out.println("写入：" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    System.out.println("读取到：" + myQueue.take());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}
