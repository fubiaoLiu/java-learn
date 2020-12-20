package com.xiaoliu.learn.nio;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;

/**
 * 基于FileChannel对文件加共享锁
 *
 * @author Fubiao.Liu
 * @since 2020/12/9 15:16
 **/
public class FileChannelSharedLock {
    public static void main(String[] args) {
        try (// 构造一个传统的文件输出流
             FileInputStream fis = new FileInputStream("D:\\tmp\\hello.txt");
             // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
             FileChannel channel = fis.getChannel()) {
            // 对整个文件加共享锁
            channel.lock(0, Integer.MAX_VALUE, true);
            // 对文件中指定范围的数据加共享锁
            // channel.lock(0, 1, true);
            Thread.sleep(60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}