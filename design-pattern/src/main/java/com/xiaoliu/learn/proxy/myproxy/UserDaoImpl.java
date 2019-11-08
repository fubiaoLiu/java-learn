package com.xiaoliu.learn.proxy.myproxy;

/**
 * @description: 实现类
 * @author: FuBiaoLiu
 * @date: 2019/11/6
 */
public class UserDaoImpl implements UserDao, LifeCycle {
    @Override
    public void query() {
        System.out.println("UserDaoImpl#query");
    }

    @Override
    public String queryName() {
        return "UserDaoImpl#queryName";
    }

    @Override
    public String queryNameById(int id) {
        return "UserDaoImpl#queryNameById(" + id + ")";
    }

    @Override
    public String queryByName(String name) {
        return "UserDaoImpl#queryByName(" + name + ")";
    }

    @Override
    public void start() {
        System.out.println("--------start-------");
    }

    @Override
    public void stop() {
        System.out.println("--------stop-------");
    }
}
