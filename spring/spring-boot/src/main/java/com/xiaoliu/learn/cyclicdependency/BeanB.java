package com.xiaoliu.learn.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanB {
    @Autowired
    public BeanA beanA;
}
