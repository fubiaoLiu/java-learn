package com.xiaoliu.learn.proxy;

import com.xiaoliu.learn.proxy.myproxy.*;

import java.lang.reflect.Proxy;

/**
 * @description: 启动程序
 * @author: FuBiaoLiu
 * @date: 2019/10/23
 */
public class Start {
    public static void main(String[] args) {
        // runStaticProxy();
        // runJDKDynamicProxy();
        runSimpleProxy();
        runMyProxy();
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

    /**
     * 模拟JDK动态代理(简单版本): 单接口、没有import
     */
    private static void runSimpleProxy() {
        UserDao target = new UserDaoImpl();
        UserDao proxy = (UserDao) SimpleProxy.newProxyInstance(target);
        proxy.query();
        System.out.println(proxy.queryNameById(1));
        System.out.println(proxy.queryByName("小刘"));
    }

    /**
     * 模拟JDK动态代理
     */
    private static void runMyProxy() {
        CustomInvocationHandler handler = new DefaultCustomInvocationHandler(new UserDaoImpl());
        Object proxy = MyProxy.newProxyInstance(new Class[]{UserDao.class, LifeCycle.class}, handler);
        ((UserDao) proxy).query();
        System.out.println(((UserDao) proxy).queryName());
        System.out.println(((UserDao) proxy).queryNameById(1));
        System.out.println(((UserDao) proxy).queryByName("小刘"));
        ((LifeCycle) proxy).start();
    }
}
