package com.xiaoliu.learn.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.StringJoiner;

/**
 * 基于FileChannel从文件读取数据
 *
 * @author Fubiao.Liu
 * @since 2020/12/9 13:41
 **/
public class FileChannelReadFile {
    public static void main(String[] args) {
        try (
                // 构造个传统的文件输入流
                FileInputStream fis = new FileInputStream("D:\\tmp\\hello.txt");
                // 通过文件输入流获取到对应的FileChannel，以NIO的方式来读文件
                FileChannel channel = fis.getChannel()) {
            // channel必然会从buffer的position = 0的位置开始读起，一直读到limit，limit = 字符串字节数组的长度
            // buffer的position就和limit一样了
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12);
            int read = channel.read(byteBuffer);
            if (read != -1) {
                // 接收的数组长度不够，则保留前面的数据
                // 长度超过buffer长度报BufferUnderflowException异常
                byte[] dst = new byte[12];
                byteBuffer.rewind();
                byteBuffer.get(dst);
                System.out.println(new String(dst));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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