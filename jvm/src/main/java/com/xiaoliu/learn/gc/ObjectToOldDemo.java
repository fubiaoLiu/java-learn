package com.xiaoliu.learn.gc;

/**
 * 模拟对象进入老年代
 * <p>
 * JVM启动参数:
 * -XX:NewSize=10485760
 * -XX:MaxNewSize=10485760
 * -XX:InitialHeapSize=20971520
 * -XX:MaxHeapSize=20971520
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=4194304
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -Xloggc:jvm\log\gc\ObjectToOldDemo.log
 *
 * @author: FuBiaoLiu
 * @date: 2020/2/14
 */
public class ObjectToOldDemo {
    public static void main(String[] args) {
        dynamicAgeJudgment();

//        fifteenYearsOld();

//        biggerThanSurvivor();

//        largeObjects();
    }

    /**
     * 动态年龄判断
     * <p>
     * 程序启动后产生了2M多数据(数据多少跟JKD版本等有关系，不同的版本此处的代码可能需要调整)，
     * 所以在创建了 2 * 2M + 128kb 的对象后再创建 2M 的对象会触发一次YGC。
     */
    private static void dynamicAgeJudgment() {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        // array1 = new byte[2 * 1024 * 1024];
        array1 = null;
        byte[] array2 = new byte[128 * 1024];

        // 触发第一次YGC，array2和一些其他的数据(大概可能会有七八百Kb - 大于50%)会放入Survivor区
        byte[] array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[2 * 1024 * 1024];
        array3 = null;

        // 触发第二次YGC，上一次GC放进Survivor区的对象此时会触发动态年龄判断，
        // 因为大小大于Survivor的50%并且年龄都为1，所以所有对象进入老年代
        byte[] array4 = new byte[2 * 1024 * 1024];
    }

    /**
     * 对象到达15岁后自然进入老年代
     */
    private static void fifteenYearsOld() {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        // array1 = new byte[2 * 1024 * 1024];
        array1 = null;

        // 触发第一次YGC
        byte[] targetObj = new byte[2 * 1024 * 1024];
        targetObj = new byte[128 * 1024];

        // 第一次触发YGC时，targetObj还没创建。
        // 所以在第i次触发YGC时，targetObj的年龄为i-1，并且在targetObj年龄为15之后，再触发YGC targetObj才会进入老年代
        for (int i = 2; i <= 17; i++) {
            byte[] array2 = new byte[2 * 1024 * 1024];
            array2 = new byte[2 * 1024 * 1024];
            array2 = null;
            // 触发第i次YGC，targetObj的年龄为i-1
            array2 = new byte[2 * 1024 * 1024];
        }
    }

    /**
     * YGC后存活对象太多，Survivor放不下直接进入老年代(这种场景下会有部分对象留在Survivor)
     */
    private static void biggerThanSurvivor() {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];

        // 程序启动后产生了2M多数据，所以创建array2时会触发第一次YGC，array1会直接进入老年代
        // 而其他未知对象(大概700kb左右)进入了Survivor
        byte[] array2 = new byte[2 * 1024 * 1024];
    }

    /**
     * YGC后存活对象太多，Survivor放不下直接进入老年代(这种场景下会有部分对象留在Survivor)
     */
    private static void biggerThanSurvivor1() {
        byte[] array1 = new byte[2 * 1024 * 1024];
        byte[] array2 = new byte[2 * 1024 * 1024];

        // 程序启动后产生了2M多数据，所以创建array3时会触发第一次YGC，array1和array2会直接进入老年代，而其他未知对象(大概700kb左右)进入了Survivor
        byte[] array3 = new byte[2 * 1024 * 1024];
    }

    /**
     * 大对象( 大于4M )直接进入老年代
     */
    private static void largeObjects() {
        byte[] array1 = new byte[4 * 1024 * 1024];
    }
}
