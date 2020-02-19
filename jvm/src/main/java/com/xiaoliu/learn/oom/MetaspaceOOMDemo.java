package com.xiaoliu.learn.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 模拟 metaspace 内存溢出
 * JVM启动参数:
 * -XX:MetaspaceSize=10m
 * -XX:MaxMetaspaceSize=10m
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -Xloggc:jvm\log\oom\MetaspaceOOMDemo.log
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=./
 *
 * @author: FuBiaoLiu
 * @date: 2020/2/16
 */
public class MetaspaceOOMDemo {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")) {
                        System.out.println("准备启动，自动安全扫描...");
                    }
                    return methodProxy.invokeSuper(o, objects);
                }
            });

            Car car = (Car) enhancer.create();
            car.run();
        }
    }

    static class Car {
        public void run() {
            System.out.println("汽车启动，出发...");
        }
    }
}
