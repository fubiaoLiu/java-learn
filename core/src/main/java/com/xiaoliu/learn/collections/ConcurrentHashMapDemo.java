package com.xiaoliu.learn.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 1.8 ConcurrentHashMap 的死循环BUG，原因是递归创建 ConcurrentHashMap 对象。1.9修复了
 * @author: FuBiaoLiu
 * @date: 2019/9/18
 */
public class ConcurrentHashMapDemo {
    private Map<Integer, Integer> cache = new ConcurrentHashMap<>(16);

    public static void main(String[] args) {
        ConcurrentHashMapDemo ch = new ConcurrentHashMapDemo();
        System.out.println(ch.fibonaacci(80));
    }

    private int fibonaacci(Integer i) {
        if (i == 0 || i == 1) {
            return i;
        }

        return cache.computeIfAbsent(i, (key) -> {
            System.out.println("fibonaacci : " + key);
            return fibonaacci(key - 1) + fibonaacci(key - 2);
        });

    }
}
