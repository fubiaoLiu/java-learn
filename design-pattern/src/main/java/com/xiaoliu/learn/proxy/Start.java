package com.xiaoliu.learn.proxy;

import java.lang.reflect.Proxy;

/**
 * @description: 启动程序
 * @author: FuBiaoLiu
 * @date: 2019/10/23
 */
public class Start {
    public static void main(String[] args) {
        // runStaticProxy();
        runJDKDynamicProxy();
    }

    /**
     * 静态代理
     */
    private static void runStaticProxy() {
        Dao target = new DaoImpl();
        StaticProxy proxy = new StaticProxy(target);
        proxy.insert();
    }

    /**
     * JDK动态代理
     */
    private static void runJDKDynamicProxy() {
        Dao target = new DaoImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);
        Dao proxy = (Dao) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{Dao.class}, handler);
        proxy.insert();
    }
}
