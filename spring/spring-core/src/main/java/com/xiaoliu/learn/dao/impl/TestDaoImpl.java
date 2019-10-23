package com.xiaoliu.learn.dao.impl;

import com.xiaoliu.learn.dao.TestDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description: Bean生命周期的回调(CommonAnnotationBeanPostProcessor)
 * 1、使用@PostConstruct、@PreDestroy注解
 * 2、实现InitializingBean、DisposableBean接口
 * 3、在XML文件中配置(<bean>标签的init-method、destroy-method)
 * @author: FuBiaoLiu
 * @date: 2019/10/15
 */
@Repository
public class TestDaoImpl implements TestDao, InitializingBean, DisposableBean {
    public TestDaoImpl() {
        System.out.println("Constructor");
    }

    @PostConstruct
    private void init() {
        System.out.println("PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    // init-method

    @PreDestroy
    private void preDestroy() {
        System.out.println("PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void test() {
        System.out.println("TestDaoImpl#test");
    }

}
