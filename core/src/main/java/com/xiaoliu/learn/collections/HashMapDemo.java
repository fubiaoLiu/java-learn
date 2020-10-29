package com.xiaoliu.learn.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: HashMap测试
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
public class HashMapDemo {

    public static void main(String[] args) {
        testCapacity1();
        testTableSizeFor();
    }

    /**
     * 测试tableSizeFor()方法
     */
    public static void testCapacity1() {
        Map<String, String> map = new HashMap<>(1);
        map.put("1", "1");
    }

    /**
     * 测试tableSizeFor()方法
     */
    public static void testTableSizeFor() {
        System.out.println(tableSizeFor(-1));
        System.out.println(tableSizeFor(0));
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(2));
        System.out.println(tableSizeFor(4));
        System.out.println(tableSizeFor(8));
        System.out.println(tableSizeFor(10));
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(32));
        System.out.println(tableSizeFor(33));
    }

    /**
     * {@link HashMap} 中的tableSizeFor(int)
     *
     * @param cap 所需容量
     * @return 优化后的容量
     */
    private static int tableSizeFor(int cap) {
        // cap = 10
        int n = cap - 1;
        System.out.println("1:" + n);
        // n = 9    1001
        n |= n >>> 1;
        System.out.println("2:" + n);
        // n >>> 1 = 0100
        // n |= n >>> 1 = 1101
        n |= n >>> 2;
        System.out.println("3:" + n);
        // n >>> 2 = 0011
        // n |= n >>> 2 = 1111
        n |= n >>> 4;
        System.out.println("4:" + n);
        // n >>> 4 = 0000
        // n |= n >>> 4 = 1111
        n |= n >>> 8;
        System.out.println("5:" + n);
        // n >>> 8 = 0000
        // n |= n >>> 8 = 1111
        n |= n >>> 16;
        System.out.println("6:" + n);
        // n >>> 16 = 0000
        // n |= n >>> 16 = 1111
        return (n < 0) ? 1 : n + 1;
        // n + 1 = 00001 0000 = 16
    }
}
