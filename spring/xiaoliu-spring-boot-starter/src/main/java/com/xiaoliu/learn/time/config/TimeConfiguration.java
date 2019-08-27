package com.xiaoliu.learn.time.config;

import com.xiaoliu.learn.time.service.TimeService;
import org.springframework.context.annotation.Bean;

/**
 * @description: clock config
 * @author: FuBiaoLiu
 * @date: 2019/8/27
 */
public class TimeConfiguration {
    @Bean
    public TimeService clockService() {
        return new TimeService();
    }
}
