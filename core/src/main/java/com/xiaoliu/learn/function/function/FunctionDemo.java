package com.xiaoliu.learn.function.function;

import java.util.function.Function;

/**
 * @description: Function<T, R> 代表函数，提供一个T类型的输入参数，R类型的输出。
 * 它包含一个有输入输出的apply方法，还有compose、andThen、indentity三个方法。
 * @author: FuBiaoLiu
 * @date: 2019/9/19
 */
public class FunctionDemo {
    private static Function<Integer, Integer> increment = i -> ++i;
    private static Function<Integer, Integer> leftShift = i -> i << 1;

    public static void main(String[] args) {
        apply();
        compose();
        andThen();
        identity();
    }

    /**
     * 对给定的参数执行操作
     */
    private static void apply() {
        Function<Integer, Integer> r = i -> i * 3 + 1;
        System.out.println("apply:" + r.apply(2));
    }

    /**
     * compose():返回一个组合函数，先执行leftShift方法，再执行increment把leftShift的输出作为increment的输入
     */
    private static void compose() {
        System.out.println("compose:" + increment.compose(leftShift).apply(2));
    }

    /**
     * andThen():返回一个组合函数，先执行increment方法，再执行leftShift把increment的输出作为leftShift的输入
     */
    private static void andThen() {
        System.out.println("andThen:" + increment.andThen(leftShift).apply(2));
    }

    /**
     * identity():返回一个不进行任何处理的Function
     */
    private static void identity() {
        System.out.println("identity:" + Function.identity().apply(2));
    }
}
