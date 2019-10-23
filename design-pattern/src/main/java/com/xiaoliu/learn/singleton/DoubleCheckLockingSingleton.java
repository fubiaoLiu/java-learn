package com.xiaoliu.learn.singleton;

/**
 * @description: 双重校验锁定
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public class DoubleCheckLockingSingleton {
    private static DoubleCheckLockingSingleton instance = null;

    private DoubleCheckLockingSingleton() {
    }

    public static DoubleCheckLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                System.out.println(DoubleCheckLockingSingleton.getInstance());
            }).start();
        }
    }
}
