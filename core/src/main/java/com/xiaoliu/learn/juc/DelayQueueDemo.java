package com.xiaoliu.learn.juc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * PriorityBlockingQueue效果演示小程序
 * 基于优先级队列实现的线程安全的延迟队列
 *
 * @author Fubiao.Liu
 * @since 2020/11/18 19:15
 **/
public class DelayQueueDemo {
    public static void main(String[] args) throws Exception {
        DelayQueue<Element> queue = new DelayQueue<>();
        queue.offer(new Element(5, "node_0"));
        queue.offer(new Element(3, "node_1"));
        queue.offer(new Element(6, "node_2"));
        queue.offer(new Element(1, "node_3"));
        queue.offer(new Element(2, "node_4"));
        queue.offer(new Element(1, "node_5"));
        int size = queue.size();
        Iterator<Element> iterator = queue.iterator();
        iterator.next();
        for (int i = 0; i < size; i++) {
            queue.poll();
            System.out.println(queue.take());
        }
    }

    static class Element implements Delayed {
        long time;
        String name;

        public Element(long time, String name) {
            this.time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(time);
            this.name = name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Element element = (Element) o;
            long diff = this.time - element.time;
            // 改成>=会造成问题
            return diff <= 0 ? -1 : 1;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "time=" + time +
                    ", name='" + name + '\'' +
                    ", now='" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + '\'' +
                    '}';
        }
    }
}