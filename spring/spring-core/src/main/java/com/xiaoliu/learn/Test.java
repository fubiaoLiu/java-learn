package com.xiaoliu.learn;

import com.xiaoliu.learn.config.SpringConfig;
import com.xiaoliu.learn.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 测试类
 * @author: FuBiaoLiu
 * @date: 2019/10/12
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println("------ System init Success! ------");
        TestService service = (TestService) annotationConfigApplicationContext.getBean("myTestService");
        service.test1();
        System.out.println("----------------------------------");
        service.test2("xiaoliu");
/*        TestService service1 = (TestService) annotationConfigApplicationContext.getBean("myTestService");
        System.out.println(service.hashCode() + ":" + service1.hashCode());*/
    }
}
