package com.xiaoliu.learn.framework.strategy;

/**
 * @description: 不用策略模式
 * 场景：假设现有一个订单计价的功能，有多种计价方式。
 * 不使用策略模式缺点：
 * 多个if else，每个if else里的代码都特别长，并且if里的判断条件也可能会非常复杂。
 * 代码质量很差，可读性、维护性都极差。
 * @author: liufb
 * @create: 2020/5/17 21:13
 **/
public class WithoutStrategyPattern {
    public static void main(String[] args) {
        int discountType = 1;
        if (discountType == 1) {
            System.out.println("执行A计价方式的计价逻辑");
        } else if (discountType == 2) {
            System.out.println("执行B计价方式的计价逻辑");
        } else if (discountType == 3) {
            System.out.println("执行C计价方式的计价逻辑");
        } else if (discountType == 4) {
            System.out.println("执行D计价方式的计价逻辑");
        } else {
            System.out.println("执行默认计价方式的计价逻辑");
        }
    }
}
