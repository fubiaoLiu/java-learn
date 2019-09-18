package com.xiaoliu.learn.collections;

import java.util.HashMap;

/**
 * @description: java8中多线程put可能出现数据丢失的情况
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
public class HashMapDemo {
    private static final int SIZE = 100000;
    private static HashMap<Integer, Integer> map = new HashMap<>(2);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= SIZE; i++) {
            int result = i;
            new Thread(() -> {
                map.put(result, result);
            }, "lfb" + result).start();
        }

        // 让主线程睡眠5秒，保证上面的线程执行完毕
        Thread.sleep(5000);

        System.out.println("map.size:" + map.size());
        for (int i = 1; i <= SIZE; i++) {
            // 检测数据是否发生丢失
            Integer value = map.get(i);
            if (value == null) {
                System.out.println(i + "丢失");
            }
        }
        System.out.println("...End...");
    }
}
