package com.xiaoliu.learn.service;

import org.springframework.stereotype.Service;

/**
 * @description: 测试循环依赖，Spring默认支持单例的循环依赖，可以修改allowCircularReferences参数关闭循环依赖的功能
 * 1、构造注入时发现循环依赖抛BeanCurrentlyInCreationException异常
 * 2、setter注入时的循环依赖通过Set<String> singletonsCurrentlyInCreation缓存正在创建bean的对象，提前暴露这个临时对象来完成循环引用
 * @author: FuBiaoLiu
 * @date: 2019/10/28
 */
@Service
public class CyclicService1 {
    CyclicService2 cyclicService2;

    public CyclicService1(CyclicService2 cyclicService2) {
        this.cyclicService2 = cyclicService2;
    }

    public void test() {
        System.out.println("CyclicService1.cyclicService2=" + cyclicService2);
    }

}
