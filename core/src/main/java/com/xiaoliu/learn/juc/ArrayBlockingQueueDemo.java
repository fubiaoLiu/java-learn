package com.xiaoliu.learn.juc;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue效果演示小程序
 * 基于数组实现的线程安全的有界阻塞队列
 *
 * @author Fubiao.Liu
 * @since 2020/11/18 19:15
 **/
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        queue.offer("node_0");
        queue.offer("node_1");
        queue.offer("node_2");
        System.out.println("size:" + queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println("size:" + queue.size());
        queue.remove("node_2");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 阻塞
        queue.put("node_3");
        System.out.println(queue.take());
    }
}