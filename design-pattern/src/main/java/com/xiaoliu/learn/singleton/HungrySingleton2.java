package com.xiaoliu.learn.singleton;

/**
 * @description: 饿汉
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public class HungrySingleton2 {
    private static HungrySingleton2 instance;

    static {
        instance = new HungrySingleton2();
    }

    private HungrySingleton2() {
    }

    public static HungrySingleton2 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HungrySingleton2.getInstance());
            }).start();
        }
    }

}
