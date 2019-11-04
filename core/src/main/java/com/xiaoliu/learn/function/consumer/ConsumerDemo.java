package com.xiaoliu.learn.function.consumer;

import java.util.function.Consumer;

/**
 * @description: Consumer<T> 代表消费，提供一个T类型的输入参数，不返回执行结果。
 * 它包含有一个有输入而无输出的accept接口方法；除accept方法，它还包含有andThen这个方法。
 * @author: FuBiaoLiu
 * @date: 2019/9/19
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        accept();
        andThen();
    }

    /**
     * 对给定的参数执行操作
     */
    private static void accept() {
        StringBuilder sb = new StringBuilder("Hello ");
        Consumer<StringBuilder> consumer = (str) -> str.append("World");
        consumer.accept(sb);
        System.out.println(sb.toString());
        // Hello World
    }

    /**
     * andThen():返回一个组合函数，after将会在该函数执行之后应用
     */
    private static void andThen() {
        StringBuilder sb = new StringBuilder("Hello ");
        Consumer<StringBuilder> consumer = (str) -> str.append("World");
        Consumer<StringBuilder> consumer1 = (str) -> str.append(" !");
        consumer.andThen(consumer1).accept(sb);
        System.out.println(sb.toString());
        // Hello World !
    }
}
