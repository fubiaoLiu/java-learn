package com.xiaoliu.learn.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * @description: 重写Spring bean的生成规则: 实现BeanNameGenerator#generateBeanName()，然后修改容器的nameGenerator配置
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public class MyBeanNameGenerator implements BeanNameGenerator {
    private static final String BEAN_NAME_PREFIX = "my";

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        /**
         * default: AnnotationBeanNameGenerator
         * String beanClassName = definition.getBeanClassName();
         * Assert.state(beanClassName != null, "No bean class name set");
         * String shortClassName = ClassUtils.getShortName(beanClassName);
         * return Introspector.decapitalize(shortClassName);
         */
        String beanClassName = beanDefinition.getBeanClassName();
        Assert.state(beanClassName != null, "No bean class name set");
        return BEAN_NAME_PREFIX + ClassUtils.getShortName(beanClassName);
    }
}
