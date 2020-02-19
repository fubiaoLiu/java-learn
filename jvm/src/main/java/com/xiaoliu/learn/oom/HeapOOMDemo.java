package com.xiaoliu.learn.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟 堆 内存溢出
 * JVM启动参数:
 * -XX:NewSize=5242880
 * -XX:MaxNewSize=5242880
 * -XX:InitialHeapSize=10485760
 * -XX:MaxHeapSize=10485760
 * -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=10485760
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -Xloggc:jvm\log\oom\HeapOOMDemo.log
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=./
 *
 * 控制台输出:
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3210)
 * 	at java.util.Arrays.copyOf(Arrays.java:3181)
 * 	at java.util.ArrayList.grow(ArrayList.java:265)
 * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
 * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
 * 	at java.util.ArrayList.add(ArrayList.java:462)
 * 	at com.xiaoliu.learn.oom.HeapOOMDemo.main(HeapOOMDemo.java:38)
 *
 * @author: FuBiaoLiu
 * @date: 2020/2/16
 */
public class HeapOOMDemo {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }
}
