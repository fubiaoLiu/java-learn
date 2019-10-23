package com.xiaoliu.learn.singleton;

/**
 * @description: 枚举单例
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public enum EnumSingleton {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
