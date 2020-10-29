package com.xiaoliu.learn.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: LinkedList demo
 * @author: liufb
 * @create: 2020/9/22 14:59
 **/
public class LinkedListDemo {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        System.out.println(list.size());
    }
}
