package com.xiaoliu.learn.addr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: 地址属性
 * @author: FuBiaoLiu
 * @date: 2019/8/27
 */
@ConfigurationProperties(prefix = "xiaoliu.addr")
public class AddrProperties {
    private String ip = "127.0.0.1";
    private int port = 80;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
