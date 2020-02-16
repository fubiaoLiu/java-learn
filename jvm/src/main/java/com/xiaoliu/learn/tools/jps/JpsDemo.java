package com.xiaoliu.learn.tools.jps;

/**
 * @description: 模拟用户运行程序，jps命令查看运行中的Java进程
 * @author: FuBiaoLiu
 * @date: 2019/8/20
 */
public class JpsDemo {
    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println(1);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
