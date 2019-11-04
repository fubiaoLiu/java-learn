package com.xiaoliu.learn.cyclicdependency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 启动程序
 * @author: FuBiaoLiu
 * @date: 2019/11/4
 */
public class TestApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        // 按顺序加载指定的Bean，等同配置类加注解'@Import({Service.class, Config.class, BeanB.class})'
        ac.register(Service.class, Config.class, BeanB.class);
        ac.refresh();
        System.out.println(ac.getBean(BeanA.class));
    }
}
