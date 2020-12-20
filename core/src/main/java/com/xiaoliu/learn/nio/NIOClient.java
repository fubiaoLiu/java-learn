package com.xiaoliu.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * NIO实现客户端
 *
 * @author Fubiao.Liu
 * @since 2020/12/10 13:32
 **/
public class NIOClient {
    private static CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
    private static CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            new Worker().start();
        }
    }

    static class Worker extends Thread {
        @Override
        public void run() {
            // SocketChannel，底层封装了一个Socket
            // SocketChannel是连接到底层的Socket网络连接上去的数据通道，就是负责基于网络读写数据的
            try (SocketChannel channel = SocketChannel.open();
                 Selector selector = Selector.open()) {
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress("localhost", 8800));
                // 一定是发起了一个TCP三次握手，尝试建立连接
                // 后台一定跟server端进行三次握手，握手成功之后建立了一个连接

                // 监听connect行为
                channel.register(selector, SelectionKey.OP_CONNECT);

                while (true) {
                    // 服务器程序一定会给客户端返回一个响应
                    selector.select();

                    Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();
                    while (keysIterator.hasNext()) {
                        SelectionKey key = keysIterator.next();
                        keysIterator.remove();

                        // 如果说server返回的是一个connectable的消息
                        if (key.isConnectable()) {
                            if (channel.isConnectionPending()) {
                                if (channel.finishConnect()) {
                                    // 接下来对这个SocketChannel感兴趣的就是server返回回来的数据了
                                    // READ事件，可以读数据的事件
                                    // 一旦建立连接成功了以后，此时就可以给server发送一个请求了
                                    key.interestOps(SelectionKey.OP_READ);
                                    channel.write(encoder.encode(CharBuffer.wrap("你好")));
                                } else {
                                    key.cancel();
                                }
                            }
                        } else if (key.isReadable()) {
                            // 服务器端返回了一条数据可以读了
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            // 把数据写入buffer，position推进到读取的字节数数字
                            channel.read(byteBuffer);
                            byteBuffer.flip();
                            CharBuffer charBuffer = decoder.decode(byteBuffer);
                            String response = charBuffer.toString();
                            System.out.println("[" + Thread.currentThread().getName() + "]收到响应：" + response);
                            channel.write(encoder.encode(CharBuffer.wrap("你好")));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}