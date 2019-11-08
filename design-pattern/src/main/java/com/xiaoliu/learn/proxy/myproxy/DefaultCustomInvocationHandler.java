package com.xiaoliu.learn.proxy.myproxy;

import java.lang.reflect.Method;

/**
 * @description: 默认的自定义InvocationHandler实现
 * @author: FuBiaoLiu
 * @date: 2019/11/7
 */
public class DefaultCustomInvocationHandler implements CustomInvocationHandler {
    private Object target;

    public DefaultCustomInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Method method, Object[] args) throws Throwable {
        System.out.println("DefaultCustomInvocationHandler#invoke");
        return method.invoke(target, args);
    }
}
