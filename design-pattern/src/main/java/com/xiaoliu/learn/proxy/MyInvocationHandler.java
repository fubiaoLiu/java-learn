package com.xiaoliu.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: FuBiaoLiu
 * @date: 2019/10/23
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MyInvocationHandler#invoke-start");
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        System.out.println("Target#method执行耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("MyInvocationHandler#invoke-end");
        return result;
    }
}
