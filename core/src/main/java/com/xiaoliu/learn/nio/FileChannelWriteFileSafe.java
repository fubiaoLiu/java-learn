package com.xiaoliu.learn.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 基于FileChannel多线程并发写线程安全测试
 *
 * @author Fubiao.Liu
 * @since 2020/12/9 14:09
 **/
public class FileChannelWriteFileSafe {
    public static void main(String[] args) {
        try {
            // 构造一个传统的文件输出流
            FileOutputStream fos = new FileOutputStream("D:\\tmp\\helloSafe.txt");
            // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
            FileChannel channel = fos.getChannel();
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    ByteBuffer byteBuffer = ByteBuffer.wrap("Hello World! ".getBytes());
                    try {
                        channel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}