package com.xiaoliu.learn.singleton;

/**
 * @description:
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public class HolderSingleton {
    private HolderSingleton() {
    }

    public static HolderSingleton getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static HolderSingleton instance = new HolderSingleton();
    }
}
