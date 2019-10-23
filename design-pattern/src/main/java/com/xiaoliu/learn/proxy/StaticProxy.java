package com.xiaoliu.learn.proxy;

/**
 * @description: 静态代理的代理对象proxy
 * @author: FuBiaoLiu
 * @date: 2019/10/23
 */
public class StaticProxy implements Dao {
    private Dao dao;

    public StaticProxy(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void insert() {
        System.out.println("StaticProxy#insert-start");
        dao.insert();
        System.out.println("StaticProxy#insert-end");
    }
}
