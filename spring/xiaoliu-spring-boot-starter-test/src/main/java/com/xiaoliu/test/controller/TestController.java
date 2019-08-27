package com.xiaoliu.test.controller;

import com.xiaoliu.learn.addr.service.AddrService;
import com.xiaoliu.learn.time.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试controller
 * @author: FuBiaoLiu
 * @date: 2019/8/27
 */
@RestController
@RequestMapping("/v2/test")
public class TestController {
    private AddrService addrService;
    private TimeService timeService;

    @Autowired
    public TestController(AddrService addrService, TimeService timeService) {
        this.addrService = addrService;
        this.timeService = timeService;
    }

    @PostMapping(value = "/addr")
    public void testAddr() {
        addrService.showAddr();
    }

    @PostMapping(value = "/clock")
    public void testClock() {
        timeService.showTime();
    }
}
