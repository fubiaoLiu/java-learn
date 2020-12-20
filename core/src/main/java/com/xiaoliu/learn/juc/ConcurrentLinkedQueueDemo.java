package com.xiaoliu.learn.juc;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue效果演示小程序
 * 线程安全的队列
 *
 * @author Fubiao.Liu
 * @since 2020/11/18 19:15
 **/
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
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
    }
}