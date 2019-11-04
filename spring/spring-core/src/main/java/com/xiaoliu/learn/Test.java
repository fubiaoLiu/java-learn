package com.xiaoliu.learn;

import com.xiaoliu.learn.config.SpringConfig;
import com.xiaoliu.learn.service.CyclicService1;
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
        annotationConfigApplicationContext.setAllowCircularReferences(false);
        System.out.println("------ System init Success! ------");
        // test1(annotationConfigApplicationContext);
        test2(annotationConfigApplicationContext);
    }

    private static void test1(AnnotationConfigApplicationContext applicationContext) {
        TestService service = (TestService) applicationContext.getBean("myTestService");
        service.test1();
        System.out.println("----------------------------------");
        service.test2("xiaoliu");
        /* TestService service1 = (TestService) annotationConfigApplicationContext.getBean("myTestService");
        System.out.println(service.hashCode() + ":" + service1.hashCode());*/
    }

    /**
     * 测试循环依赖的情况
     * @param applicationContext
     */
    private static void test2(AnnotationConfigApplicationContext applicationContext) {
        CyclicService1 service = applicationContext.getBean(CyclicService1.class);
        service.test();
    }
}
