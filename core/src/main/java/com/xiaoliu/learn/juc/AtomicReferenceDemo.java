package com.xiaoliu.learn.juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 原子引用
 * @author: FuBiaoLiu
 * @date: 2019/11/20
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        String str1 = "str1";
        String str2 = "str2";

        AtomicReference<String> atomicStringReference = new AtomicReference<>(str1);

        System.out.println("exchanged1: " + atomicStringReference.compareAndSet(str1, str2));

        System.out.println("exchanged2: " + atomicStringReference.compareAndSet(str1, str2));
    }
}
