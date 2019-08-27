package com.xiaoliu.learn.addr.service;

/**
 * @description:
 * @author: FuBiaoLiu
 * @date: 2019/8/27
 */
public class AddrService {
    private String ip;
    private int port;

    public AddrService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void showAddr() {
        System.out.println("http://" + ip + ":" + port);
    }
}
