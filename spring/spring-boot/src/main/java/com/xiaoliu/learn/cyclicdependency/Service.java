package com.xiaoliu.learn.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 默认BeanA在前，BeanB在后，会出现循环依赖问题
 * @author: FuBiaoLiu
 * @date: 2019/11/4
 */
public class Service {
    @Autowired
    private BeanA beanA;

    @Autowired
    private BeanB beanB;
}
