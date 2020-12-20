package com.xiaoliu.learn.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList效果演示小程序
 * 线程安全的ArrayList
 *
 * @author Fubiao.Liu
 * @since 2020/11/18 19:15
 **/
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("element_0");
        list.add("element_1");
        list.add("element_2");
        System.out.println(list.get(0));
        System.out.println(list.size());

        list.set(0,"element_0_new");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        list.remove(0);
    }
}