package com.xiaoliu.learn.start;

/**
 * @description: 应用程序启动类
 * @author: FuBiaoLiu
 * @date: 2019/10/17
 */
public class WebApplicationStart {
	public static void main(String[] args) {
		webApplicationRun();
//		springApplicationRun();
	}

	public static void webApplicationRun() {
		WebApplication.run();
	}

	public static void springApplicationRun() {
		SpringApplication.run();
	}
}
