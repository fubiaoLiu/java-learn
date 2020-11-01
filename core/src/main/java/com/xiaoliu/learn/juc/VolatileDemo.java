package com.xiaoliu.learn.juc;

/**
 * volatile关键字效果演示小程序
 *
 * @author Fubiao.Liu
 * @since 2020/10/29 15:56
 **/
public class VolatileDemo {
    /**
     * 核心在这里，分别 去掉/加上 volatile 运行看看打印效果
     */
    private static volatile int flag = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localFlag = flag;
            while (true) {
                if (localFlag != flag) {
                    System.out.println("监听到flag变更：" + flag);
                    localFlag = flag;
                }
            }
        }).start();

        new Thread(() -> {
            int localFlag = flag;
            while (true) {
                System.out.println("更新flag为：" + ++localFlag);
                flag = localFlag;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
