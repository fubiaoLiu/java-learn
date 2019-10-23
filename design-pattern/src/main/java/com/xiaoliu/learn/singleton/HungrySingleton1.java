package com.xiaoliu.learn.singleton;

/**
 * @description: 饿汉
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public class HungrySingleton1 {
    private static HungrySingleton1 instance = new HungrySingleton1();

    private HungrySingleton1() {
    }

    public static HungrySingleton1 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HungrySingleton1.getInstance());
            }).start();
        }
    }

}
