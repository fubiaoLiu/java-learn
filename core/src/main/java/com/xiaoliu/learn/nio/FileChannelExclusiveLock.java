package com.xiaoliu.learn.nio;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 基于FileChannel对文件加独占锁
 *
 * @author Fubiao.Liu
 * @since 2020/12/9 15:16
 **/
public class FileChannelExclusiveLock {
    public static void main(String[] args) {
        try (// 构造一个传统的文件输出流
             FileOutputStream fos = new FileOutputStream("D:\\tmp\\hello.txt");
             // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
             FileChannel channel = fos.getChannel()) {
            // 对整个文件加独占锁
            // channel.lock();
            // 对文件中指定范围的数据加独占锁
            channel.lock(0, 1, false);
            Thread.sleep(60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}