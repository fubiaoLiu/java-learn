package com.xiaoliu.learn;

import com.xiaoliu.learn.cyclicdependency.BeanB;
import com.xiaoliu.learn.cyclicdependency.Config;
import com.xiaoliu.learn.cyclicdependency.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @description: 启动类
 * @author: FuBiaoLiu
 * @date: 2019/8/27
 */
@SpringBootApplication
@Import({Service.class, Config.class, BeanB.class})
//@ComponentScan(basePackages = {"com.xiaoliu"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}


