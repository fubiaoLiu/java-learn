package com.xiaoliu.learn.proxy.myproxy;

/**
 * @description: 接口
 * @author: FuBiaoLiu
 * @date: 2019/11/6
 */
public interface UserDao {
    void query();

    String queryName();

    String queryNameById(int id);

    String queryByName(String name);
}
