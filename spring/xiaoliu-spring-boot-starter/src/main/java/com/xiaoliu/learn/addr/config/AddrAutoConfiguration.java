package com.xiaoliu.learn.addr.config;

import com.xiaoliu.learn.addr.service.AddrService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

//@Configuration
@ConditionalOnClass(AddrService.class)
@ConditionalOnProperty(name = "xiaoliu.addr.enabled", havingValue = "true", matchIfMissing = true)
// 配置文件存在这个xiaoliu.addr.enabled=true才启动，允许不存在该配置
@EnableConfigurationProperties(AddrProperties.class)
public class AddrAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AddrService addrService(AddrProperties addrProperties) {
        return new AddrService(addrProperties.getIp(), addrProperties.getPort());
    }
}
