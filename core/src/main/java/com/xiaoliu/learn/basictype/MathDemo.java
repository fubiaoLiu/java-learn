package com.xiaoliu.learn.basictype;

public class MathDemo {
    public static void main(String[] args) {
        round();
    }

    private static void round() {
        System.out.println("Math.round(11.4):" + Math.round(11.4));
        System.out.println("Math.round(11.5):" + Math.round(11.5));
        System.out.println("Math.round(11.6):" + Math.round(11.6));
        System.out.println("Math.round(-11.4):" + Math.round(-11.4));
        System.out.println("Math.round(-11.5):" + Math.round(-11.5));
        System.out.println("Math.round(-11.6):" + Math.round(-11.6));
    }
}