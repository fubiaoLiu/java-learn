package com.xiaoliu.learn.gc;

/**
 * 模拟触发Full GC
 * <p>
 * JVM启动参数:
 * -XX:NewSize=10485760
 * -XX:MaxNewSize=10485760
 * -XX:InitialHeapSize=20971520
 * -XX:MaxHeapSize=20971520
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=3145728‬
 * -XX:CMSInitiatingOccupancyFraction=92
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -Xloggc:jvm\log\gc\FullGCDemo.log
 *
 * @author: FuBiaoLiu
 * @date: 2020/2/14
 */
public class FullGCDemo {
    public static void main(String[] args) {
        objectsMoreThanOldAvailableSpace();

//        oldUsedSpaceExceed92();

//        tooManyObjectsLiveOfYGC();
    }

    /**
     * 老年代可用空间小于新生代对象总大小 或 老年代可用空间小于历次YGC升入老年代对象的平均大小
     */
    private static void objectsMoreThanOldAvailableSpace() {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];

        // 第一次触发YGC，2M对象会进入老年代
        array1 = new byte[2 * 1024 * 1024];

        for (int i = 2; i <= 5; i++) {
            array1 = new byte[2 * 1024 * 1024];
            array1 = new byte[2 * 1024 * 1024];

            // 第i次触发YGC，每次会有2M对象进入老年代。
            // 第5次，老年代可用空间不足2M，会先触发FGC，再触发YGC。
            array1 = new byte[2 * 1024 * 1024];
        }
    }

    /**
     * 老年代占用空间大于92%
     * 发现只有在后面停顿等待一段时间才能看到FGC，猜测该条件下FGC的触发是由deamon进程控制的。
     */
    private static void oldUsedSpaceExceed92() {
        byte[] array1 = new byte[3 * 1024 * 1024];
        array1 = new byte[3 * 1024 * 1024];
        array1 = new byte[3300 * 1024];
        array1 = null;

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * YGC后存活对象过多，老年代放不下
     * <p>
     * 0.135: [GC (Allocation Failure) 0.135: [ParNew (promotion failed): 6295K->6989K(9216K), 0.0017629 secs]0.137: [CMS: 8194K->4774K(10240K), 0.0033723 secs] 12439K->4774K(19456K), [Metaspace: 3465K->3465K(1056768K)], 0.0053284 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * Heap
     * par new generation   total 9216K, used 2130K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     * eden space 8192K,  26% used [0x00000000fec00000, 0x00000000fee14930, 0x00000000ff400000)
     * from space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     * to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     * concurrent mark-sweep generation total 10240K, used 4774K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     * Metaspace       used 3472K, capacity 4496K, committed 4864K, reserved 1056768K
     * class space    used 381K, capacity 388K, committed 512K, reserved 1048576K
     */
    private static void tooManyObjectsLiveOfYGC() {
        byte[] array1 = new byte[6 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];

        // 触发YGC，YGC后存活对象过多，老年代放不下，接着触发FGC
        // CMS: 8194K->4774K(10240K)过程拆解: 之所以最开始是8194k是因为YGC会依次把对象放入老年代，放的时候发现内存不够才触发FGC。比如:
        // 先放第一个 2M 数组，成功
        // 接着放第二个 2M 数组，发现内存不够，触发FGC，所以此时老年代内存占用为8M。
        byte[] array4 = new byte[2 * 1024 * 1024];
    }
}
