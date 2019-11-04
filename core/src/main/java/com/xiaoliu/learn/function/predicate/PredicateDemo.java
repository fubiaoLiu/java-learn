package com.xiaoliu.learn.function.predicate;

import java.util.function.Predicate;

/**
 * @description: Predicate<T> 代表判断，判断某个东西是否满足某种条件。
 * 它包含test方法，根据输入值来做逻辑判断，其结果为True或者False。
 * @author: FuBiaoLiu
 * @date: 2019/9/19
 */
public class PredicateDemo {

    public static void main(String[] args) {
        Predicate<String> startWith = i -> i.startsWith("x");
        Predicate<String> endsWith = i -> i.endsWith("u");

        // 直接执行startWith方法
        System.out.println("test:" + startWith.test("xiaoliu"));

        // negate: 对原来的Predicate取反
        System.out.println("negate:" + startWith.negate().test("xiaoliu"));

        // and: 针对同一输入值，多个Predicate均返回True时返回True，否则返回False
        System.out.println("and:" + startWith.and(endsWith).test("xiaoliu"));
        System.out.println("and:" + startWith.and(endsWith).test("xl"));

        // or: 针对同一输入值，多个Predicate有一个返回True就返回True，否则返回False
        System.out.println("or:" + startWith.or(endsWith).test("xiaoliu"));
        System.out.println("or:" + startWith.or(endsWith).test("xl"));
        System.out.println("or:" + startWith.or(endsWith).test("lx"));
    }

}
