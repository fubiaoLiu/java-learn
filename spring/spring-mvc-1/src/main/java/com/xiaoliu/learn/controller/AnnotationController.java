package com.xiaoliu.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: FuBiaoLiu
 * @date: 2019/11/13
 */
@Controller
public class AnnotationController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("AnnotationController#hello");
        return "hello";
    }
}
