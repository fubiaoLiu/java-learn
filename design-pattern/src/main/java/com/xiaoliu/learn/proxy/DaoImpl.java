package com.xiaoliu.learn.proxy;

/**
 * @description: 接口实现(目标对象target)
 * @author: FuBiaoLiu
 * @date: 2019/10/23
 */
public class DaoImpl implements Dao {
    @Override
    public void insert() {
        System.out.println("DaoImpl#insert");
    }
}
