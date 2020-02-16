package com.xiaoliu.learn.tools.byteman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description: Byteman demo
 * @author: FuBiaoLiu
 * @date: 2019/9/4
 */
public class BytemanDemo {
    public static void main(String[] args) {
        new BytemanDemo().start();
    }

    private void start() {
        new Thread(() -> {
            DataInputStream in = new DataInputStream(System.in);
            BufferedReader buf = new BufferedReader(new InputStreamReader(in));
            try {
                String next = buf.readLine();
                while (next != null && !next.contains("end")) {
                    consume(next);
                    next = buf.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void consume(String text) {
        // 这是个局部变量，将会在byteman中追踪它
        final String arg = text;
        Thread thread = new Thread(() -> System.out.println("program confirm " + arg));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}