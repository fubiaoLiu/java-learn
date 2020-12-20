package com.xiaoliu.learn.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 基于FileChannel将数据写入文件
 *
 * @author Fubiao.Liu
 * @since 2020/12/9 13:41
 **/
public class FileChannelWriteFile {
    public static void main(String[] args) {
        try (
                // 构造一个传统的文件输出流，如果输出流的append为true，channel的append就为true，表示追加方式写入，写入文件时会从文件末尾开始写
                FileOutputStream fos = new FileOutputStream("D:\\tmp\\hello.txt"/*, true*/);
                // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
                FileChannel channel = fos.getChannel()) {
            // channel必然会从buffer的position = 0的位置开始读起，一直读到limit，limit = 字符串字节数组的长度
            // buffer的position就和limit一样了
            ByteBuffer byteBuffer = ByteBuffer.wrap("Hello World!".getBytes());
            channel.write(byteBuffer);

            // buffer的position在哪个位置
            System.out.println(byteBuffer.position());
            // 当前写到了文件的哪一个位置
            System.out.println(channel.position());

            // 如果想再次将buffer里的数据通过channel写入磁盘文件
            // rewind：position = 0，重新读一遍buffer
            // byteBuffer.rewind();
            // 在文件末尾追加写的方式，顺序写
            // channel.write(byteBuffer);

            // 其次如果要基于FileChannel随机写，在文件指定的位置开始写，可以调整FileChannel的position
            // 重置buffer的position，再读一遍
            // byteBuffer.rewind();
            // 设置channel的position
            // channel.position(5);
            // channel.write(byteBuffer);

            // 将数据强制刷入磁盘，每次写操作都强制刷磁盘会大幅度降低吞吐量
            channel.force(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}