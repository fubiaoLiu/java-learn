package com.xiaoliu.learn.framework.chain;

/**
 * @description: 不用责任链模式
 * 缺点：大量重复代码出现，对功能修改比较麻烦
 * @author: liufb
 * @create: 2020/5/18 9:46
 **/
public class WithoutChainPattern {
    public static void main(String[] args) {
        // 业务流程1
        System.out.println("执行模块A的功能");
        System.out.println("执行模块B的功能");
        System.out.println("执行模块C的功能");

        // 业务流程2
        System.out.println("执行模块B的功能");
        System.out.println("执行模块A的功能");
        System.out.println("执行模块C的功能");

        // ...
    }
}
