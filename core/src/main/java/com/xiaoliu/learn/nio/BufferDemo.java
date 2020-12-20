package com.xiaoliu.learn.nio;

import java.nio.ByteBuffer;

/**
 * Buffer演示小程序
 *
 * @author Fubiao.Liu
 * @since 2020/12/9 10:09
 **/
public class BufferDemo {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        printInit(byteBuffer);
        printSetPosition(byteBuffer);
        printSetMark(byteBuffer);
        printReset(byteBuffer);
    }

    private static void printInit(ByteBuffer byteBuffer) {
        System.out.println("--------------init--------------");
        printBasic(byteBuffer);
    }

    private static void printSetPosition(ByteBuffer byteBuffer) {
        System.out.println("--------------set position--------------");
        byteBuffer.position(1);
        printBasic(byteBuffer);
    }

    private static void printSetMark(ByteBuffer byteBuffer) {
        System.out.println("--------------set mark--------------");
        printBasic(byteBuffer);
        System.out.println("mark:" + byteBuffer.mark());
    }

    private static void printReset(ByteBuffer byteBuffer) {
        System.out.println("--------------set reset--------------");
        System.out.println("read data:" + byteBuffer.get());
        System.out.println("read data:" + byteBuffer.get());
        printBasic(byteBuffer);
        System.out.println("-----reset------");
        byteBuffer.reset();
        printBasic(byteBuffer);
    }

    private static void printBasic(ByteBuffer byteBuffer) {
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("position:" + byteBuffer.position());
        System.out.println("remaining:" + byteBuffer.remaining());
    }
}