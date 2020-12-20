package com.xiaoliu.learn.juc;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue效果演示小程序
 * 基于数组实现的线程安全的优先级队列
 *
 * @author Fubiao.Liu
 * @since 2020/11/18 19:15
 **/
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        PriorityBlockingQueue<Element> queue = new PriorityBlockingQueue<>();
        queue.offer(new Element(5, "node_0"));
        queue.offer(new Element(3, "node_1"));
        queue.offer(new Element(6, "node_2"));
        queue.offer(new Element(1, "node_3"));
        queue.offer(new Element(2, "node_4"));
        queue.offer(new Element(1, "node_5"));
        Element element;
        while ((element = queue.poll()) != null) {
            System.out.println(element);
        }
        /*Iterator<Element> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
    }

    private static void method1() throws Exception {
        PriorityBlockingQueue<Element> queue = new PriorityBlockingQueue<>();
        queue.offer(new Element(5, "node_0"));
        queue.offer(new Element(3, "node_1"));
        queue.offer(new Element(6, "node_2"));
        queue.offer(new Element(1, "node_3"));
        queue.offer(new Element(2, "node_4"));
        queue.offer(new Element(1, "node_5"));
        System.out.println("size:" + queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println("size:" + queue.size());
        queue.remove(new Element(1, "node_2"));
        Iterator<Element> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 阻塞
        queue.put(new Element(1, "node_6"));
        System.out.println(queue.take());
    }

    static class Element implements Comparable<Element> {
        int priority;
        String name;

        public Element(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "priority=" + priority +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Element e) {
            return Integer.compare(this.priority, e.priority);
        }
    }
}