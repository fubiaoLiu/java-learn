package com.xiaoliu.learn.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: BeanB: 依赖于 BeanA
 * @author: FuBiaoLiu
 * @date: 2019/11/4
 */
public class BeanB {
    @Autowired
    public BeanA beanA;
}
