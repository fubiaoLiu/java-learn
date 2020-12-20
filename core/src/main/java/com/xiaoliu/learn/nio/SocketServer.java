package com.xiaoliu.learn.nio;


import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Socket服务端
 *
 * @author Fubiao.Liu
 * @since 2020/12/10 13:37
 **/
public class SocketServer {
    private static Executor threadPool =
            new ThreadPoolExecutor(
                    10, 10,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(100),
                    new ThreadFactory() {
                        private AtomicInteger threadNumber = new AtomicInteger();

                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, "SocketProcessThread_" + threadNumber.getAndIncrement());
                        }
                    });

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8800);
        while (true) {
            // 会在这里阻塞，一直等待别人来建立连接
            // 如果有一个客户端跟他发起了TCP三次握手，尝试建立一个连接
            // 这里就会构建出来一个Socket，这个Socket就代表了跟某个客户端的一个TCP连接，Socket连接
            Socket socket = serverSocket.accept();

            // 如果Client要跟Server建立TCP连接，接下来基于TCP协议来传输数据，发送一个一个的TCP包过来
            // 此时Client会跟Server的端口号建立的连接
            // Client跟Server的ServerSocket之间，互相传递3次握手的TCP包，连接就建立，互相交换了一些数据

            // 旦建立了TCP连接之后，客户端就会通过IO流的方式发送数据过来，无限的流
            // 底层的TCP协议会把流式的数据拆分为一个一个的TCP包 -> 包裹在IP包里 -> 再包括在以太网包里
            // 最后通过底层的网络硬件设备以及以太网的协议，发送数据

            // 这里将请求封装到任务提交到线程池中处理
            threadPool.execute(new Task(socket));
            // new Thread(new Task(socket)).start();
        }
    }

    static class Task implements Runnable {
        private Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @SneakyThrows
        @Override
        public void run() {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            InputStreamReader inReader = new InputStreamReader(inputStream);
            char[] buf = new char[1024];
            int len;

            // Socket的输入流，相当于就是不停的读取对方通过TCP协议发送过的一个一个的TCP包
            // 再把TCP包的数据通过IO输入流的方式提供出来，就可以通过IO输入流读取的方式把TCP包的数据读出来放入JVM内存的一个缓冲数组中
            // read会阻塞在这里，尝试从这里读取数据出来

            // 这里需要反复的去读取socket流传输过来的数据，因为对方是不停的用流的方式发送数据过来的
            // 这里buf才1kb，可能才读取了1kb的数据，后面可能还跟了几百kb的数据，所以需要不停的读取
            while ((len = inReader.read(buf)) != -1) {
                System.out.println("服务端[" + Thread.currentThread().getName() + "]收到消息: " + new String(buf, 0, len));

                // 通过IO流发送响应数据回去，此时在底层会把响应数据拆分为一个一个的TCP包，回传回去
                // 客户端就可以接受到你发送的TCP包
                outputStream.write("消息已收到！".getBytes());
                outputStream.flush();
            }

            inReader.close();
            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}