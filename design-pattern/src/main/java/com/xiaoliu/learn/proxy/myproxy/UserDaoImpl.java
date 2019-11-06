package com.xiaoliu.learn.proxy.myproxy;

/**
 * @description: 实现类
 * @author: FuBiaoLiu
 * @date: 2019/11/6
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void query() {
        System.out.println("UserDaoImpl#query");
    }

    @Override
    public String queryNameById(int id) {
        return "UserDaoImpl#queryNameById(" + id + ")";
    }

    @Override
    public String queryByName(String name) {
        return "UserDaoImpl#queryByName(" + name + ")";
    }
}
