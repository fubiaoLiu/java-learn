package com.xiaoliu.learn.service;

import org.springframework.stereotype.Service;

/**
 * @description: 测试循环依赖
 * @author: FuBiaoLiu
 * @date: 2019/10/28
 */
@Service
public class CyclicService2 {
    /*CyclicService1 cyclicService1;

    public CyclicService2(CyclicService1 cyclicService1) {
        this.cyclicService1 = cyclicService1;
    }

    public void test() {
        System.out.println("CyclicService2.cyclicService1=" + cyclicService1);
    }*/
}
