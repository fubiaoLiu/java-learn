package com.xiaoliu.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO多路复用实现服务端
 *
 * @author Fubiao.Liu
 * @since 2020/12/10 13:18
 **/
public class NIOServer {
    private static CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
    private static CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

    private static ByteBuffer readBuffer;
    private static Selector selector;
    private static Executor threadPool;

    public static void main(String[] args) {
        init();
        listen();
    }

    private static void init() {
        // 读取请求数据的buffer缓冲
        readBuffer = ByteBuffer.allocate(1024);

        try (ServerSocketChannel servSocketChannel = ServerSocketChannel.open()) {
            selector = Selector.open();
            // NIO就是支持非阻塞的
            servSocketChannel.configureBlocking(false);
            // ServerSocket，就是负责去跟各个客户端连接连接请求的，backlog参数用于限制客户端的连接数
            servSocketChannel.socket().bind(new InetSocketAddress(8800), 100);
            // 把这个channel注册到selector，仅仅关注这个ServerSocketChannel接收到的TCP连接的请求
            servSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        threadPool = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadFactory() {
                    private AtomicInteger threadNumber = new AtomicInteger();

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "NIORequestProcessThread_" + threadNumber.getAndIncrement());
                    }
                });
    }

    private static void listen() {
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();

                while (keysIterator.hasNext()) {
                    SelectionKey key = keysIterator.next();
                    // 可以认为一个SelectionKey是代表了一个请求
                    keysIterator.remove();
                    threadPool.execute(new Worker(key));
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    static class Worker implements Runnable {
        private SelectionKey selectionKey;

        public Worker(SelectionKey selectionKey) {
            this.selectionKey = selectionKey;
        }

        @Override
        public void run() {
            try {
                handleKey(selectionKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleKey(SelectionKey key) throws IOException {
            SocketChannel channel = null;
            try {
                if (key.isAcceptable()) {
                    // 如果是连接的请求
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    // 调用accept方法，就可以跟客户端进行TCP三次握手
                    channel = serverChannel.accept();
                    // 三次握手成功之后，就可以获取到一个建立好TCP连接的SocketChannel
                    // 这个SocketChannel大概可以理解为，底层有一个Socket，是跟客户端进行连接的
                    // SocketChannel就是联通到那个Socket上去，负责进行网络数据的读写的
                    channel.configureBlocking(false);
                    // 仅仅关注READ请求，就是客户端发送数据过来的请求
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 如果是客户端发送数据过来请求，此时需要读取客户端发送过来的数据
                    channel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    // 通过底层的socket读取数据，写入buffer中
                    // 读取到了多少个字节，此时buffer的position就会变成多少
                    int count = channel.read(readBuffer);

                    if (count > 0) {
                        // position置为0，读取刚刚写入的数据
                        readBuffer.flip();
                        CharBuffer charBuffer = decoder.decode(readBuffer);
                        String request = charBuffer.toString();
                        System.out.println("服务端接收请求：" + request);
                        channel.write(encoder.encode(CharBuffer.wrap("收到")));
                    } else {
                        channel.close();
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
                if (channel != null) {
                    channel.close();
                }
            }
        }
    }
}