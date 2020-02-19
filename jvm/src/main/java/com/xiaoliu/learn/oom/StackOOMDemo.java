package com.xiaoliu.learn.oom;

/**
 * 模拟 栈 内存溢出
 * JVM启动参数:
 * -XX:ThreadStackSize=1M
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -Xloggc:jvm\log\oom\StackOOMDemo.log
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=./
 * <p>
 * 可以看到控制台输出的错误信息:
 * Exception in thread "main" java.lang.StackOverflowError
 * at sun.nio.cs.UTF_8$Encoder.encodeLoop(UTF_8.java:691)
 * at java.nio.charset.CharsetEncoder.encode(CharsetEncoder.java:579)
 * at sun.nio.cs.StreamEncoder.implWrite(StreamEncoder.java:271)
 * at sun.nio.cs.StreamEncoder.write(StreamEncoder.java:125)
 * at java.io.OutputStreamWriter.write(OutputStreamWriter.java:207)
 * at java.io.BufferedWriter.flushBuffer(BufferedWriter.java:129)
 * at java.io.PrintStream.write(PrintStream.java:526)
 * at java.io.PrintStream.print(PrintStream.java:669)
 * at java.io.PrintStream.println(PrintStream.java:806)
 * at com.xiaoliu.learn.oom.StackOOMDemo.work(StackOOMDemo.java:17)
 * at com.xiaoliu.learn.oom.StackOOMDemo.work(StackOOMDemo.java:18)
 * at com.xiaoliu.learn.oom.StackOOMDemo.work(StackOOMDemo.java:18)
 * at com.xiaoliu.learn.oom.StackOOMDemo.work(StackOOMDemo.java:18)
 * at com.xiaoliu.learn.oom.StackOOMDemo.work(StackOOMDemo.java:18)
 * ...
 *
 * @author: FuBiaoLiu
 * @date: 2020/2/16
 */
public class StackOOMDemo {
    private static long count = 0;

    public static void main(String[] args) {
        work();
    }

    private static void work() {
        System.out.println("第" + ++count + "次调用work方法");
        work();
    }
}
