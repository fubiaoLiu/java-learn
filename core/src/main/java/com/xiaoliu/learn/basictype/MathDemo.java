package com.xiaoliu.learn.basictype;

/**
 * @description: Math API
 * @author: FuBiaoLiu
 * @date: 2019/11/1
 */
public class MathDemo {
    public static void main(String[] args) {
        round();
    }

    private static void round() {
        System.out.println("Math.round(11.4):" + Math.round(11.4)); // 11
        System.out.println("Math.round(11.5):" + Math.round(11.5)); // 12
        System.out.println("Math.round(11.6):" + Math.round(11.6)); // 12
        System.out.println("Math.round(-11.4):" + Math.round(-11.4)); // -11
        System.out.println("Math.round(-11.5):" + Math.round(-11.5)); // -11
        System.out.println("Math.round(-11.6):" + Math.round(-11.6)); // -12
    }
}