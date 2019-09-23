package com.xiaoliu.learn.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 基于LinkedHashMap实现LRU算法
 * @author: FuBiaoLiu
 * @date: 2019/9/20
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    static class LRUMap<K, V> extends LinkedHashMap<K, V> {
        private final int maxSize;

        public LRUMap(int maxSize) {
            this(maxSize, 16, 0.75F, true);
        }

        /**
         * Constructs an empty <tt>LinkedHashMap</tt> instance with the
         * specified initial capacity, load factor and ordering mode.
         *
         * @param maxSize         the max size
         * @param initialCapacity the initial capacity
         * @param loadFactor      the load factor
         * @param accessOrder     the ordering mode - <tt>true</tt> for
         *                        access-order, <tt>false</tt> for insertion-order
         * @throws IllegalArgumentException if the initial capacity is negative
         *                                  or the load factor is nonpositive
         */
        public LRUMap(int maxSize, int initialCapacity, float loadFactor, boolean accessOrder) {
            super(initialCapacity, loadFactor, accessOrder);
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return this.size() > maxSize;
        }

    }

    private static void test1() {
        LRUMap<String, String> cache = new LRUMap(5);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");
        System.out.println("Read:" + cache.get("1"));
        cache.put("6", "6");

        for (Map.Entry entry : cache.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    private static void test2() {
        int maxSize = 5;
        LinkedHashMap<String, String> cache = new LinkedHashMap<String, String>(maxSize, 0.75F, true) {
            private static final long serialVersionUID = 4485729692063620114L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return this.size() > maxSize;
            }
        };
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");
        System.out.println("Read:" + cache.get("1"));
        cache.put("6", "6");

        for (Map.Entry entry : cache.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }
}
