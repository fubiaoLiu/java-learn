package com.xiaoliu.learn.gc;

/**
 * 模拟触发Young GC
 * <p>
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
 * -Xloggc:jvm\log\gc\YoungGCDemo.log
 *
 * @author: FuBiaoLiu
 * @date: 2020/2/14
 */
public class YoungGCDemo {
    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
    }
}
