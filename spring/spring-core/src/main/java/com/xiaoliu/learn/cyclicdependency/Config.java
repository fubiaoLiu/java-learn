package com.xiaoliu.learn.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @description: 配置类: 依赖于BeanB，定义了BeanA
 * @author: FuBiaoLiu
 * @date: 2019/11/4
 */
public class Config {
    @Autowired
    public BeanB beanB;

    @Bean
    public BeanA beanA() {
        return new BeanA();
    }
}
