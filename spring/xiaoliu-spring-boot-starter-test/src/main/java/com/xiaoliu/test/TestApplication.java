package com.xiaoliu.test;

import com.xiaoliu.learn.addr.annotation.EnableAddrService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: Starter测试服务
 * @author: FuBiaoLiu
 * @date: 2019/8/27
 */
@SpringBootApplication
@EnableAddrService
//@Import(AddrAutoConfiguration.class)
//@ComponentScan(basePackages = {"com.xiaoliu"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}


