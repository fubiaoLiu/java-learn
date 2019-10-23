package com.xiaoliu.learn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description: Spring配置类: 指定Spring Bean扫描路径，nameGenerator用于指定重写的Spring Bean Name生成器
 * @EnableAspectJAutoProxy: 启用AOP代理
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
@ComponentScan(basePackages = "com.xiaoliu.learn", nameGenerator = MyBeanNameGenerator.class)
@EnableAspectJAutoProxy // proxyTargetClass默认false，表示使用JDK代理，true表示使用CGLIB代理
public class SpringConfig {
}
