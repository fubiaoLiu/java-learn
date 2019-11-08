package com.xiaoliu.learn.proxy.myproxy;

import java.lang.reflect.Method;

/**
 * @description: 自定义InvocationHandler
 * @author: FuBiaoLiu
 * @date: 2019/11/7
 */
public interface CustomInvocationHandler {
    Object invoke(Method method, Object[] args) throws Throwable;
}
