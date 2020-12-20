package com.xiaoliu.learn.nio;

import java.nio.ByteBuffer;
import java.util.StringJoiner;

/**
 * Buffer演示小程序
 * 1、put(byte b): 往当前position位置插入一个数据
 * 2、get(): 从当前position位置获取元素
 * 3、put(byte[] src): 把src数组中的数据全部写入缓冲区
 * 4、get(byte[] dst): 从缓冲区读取全部数据放入dst数组中
 * 5、put(byte[] src,int offset,int length): 把src数组中指定的一段数据写入缓冲区
 * 6、get(byte[] dst,int offset,int length): 从缓冲区读取数据放入dst数组中
 *
 * @author Fubiao.Liu
 * @since 2020/12/9 10:09
 **/
public class BufferDemo2 {
    public static void main(String[] args) {
        // wrapArray();
        // readWrapArray();
        // allocateDirect();
        putAllocateDirect();
    }

    /**
     * 把已有的byte数组，作为核心数据放到缓冲区中去
     */
    private static void wrapArray() {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        printBasic(byteBuffer);
        byteBuffer.put((byte) 8);
        System.out.println("------put 8-------");
        printBasic(byteBuffer);
        System.out.println("get data:" + byteBuffer.get());
        byte[] dst = new byte[10];
        byteBuffer.get(dst, 2, 2);
        printArray(dst);
    }

    /**
     * 把已有的byte数组，作为核心数据放到缓冲区中去，然后从里面读取数据
     */
    private static void readWrapArray() {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        printBasic(byteBuffer);
        byte[] dst = new byte[6]; // 从position开始，读取数组容量大小的数据
        // byte[] dst = new byte[5]; // 刚好可以读取buffer中的所有数据
        // byte[] dst = new byte[6]; // 报BufferUnderflowException异常
        byteBuffer.get(dst);
        printArray(dst);
    }

    /**
     * 分配Direct缓冲区，效率更高
     */
    private static void allocateDirect() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        printBasic(byteBuffer);
        byteBuffer.put((byte) 1);
        System.out.println("------put 1-------");
        printBasic(byteBuffer);
        System.out.println("get data:" + byteBuffer.get());
        printBasic(byteBuffer);
    }

    /**
     * 分配Direct缓冲区，然后往里面put数据
     */
    private static void putAllocateDirect() {
        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4); // 报BufferOverflowException异常，缓冲区太小
        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5); // 从position位置开始放入数组数据，如果buffer和数组大小相同且position为0，则刚好塞满缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6); // 从position位置开始放入数组数据，从position开始填充数据
        printBasic(byteBuffer);
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        byteBuffer.put(bytes);
        System.out.println("------put array-------");
        printBasic(byteBuffer);
    }

    private static void printBasic(ByteBuffer byteBuffer) {
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("remaining:" + byteBuffer.remaining());
    }

    private static void printArray(byte[] array) {
        if (array == null) {
            System.out.println("null");
            return;
        }
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (byte b : array) {
            sj.add(String.valueOf(b));
        }
        System.out.println(sj.toString());
    }
}