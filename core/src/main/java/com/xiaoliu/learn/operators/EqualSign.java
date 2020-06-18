package com.xiaoliu.learn.operators;

/**
 * @description: 等号
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
public class EqualSign {
    public static void main(String[] args) {
        assignment1();
        assignment2();
    }

    /**
     * 赋值
     */
    private static void assignment1() {
        String a = "a";
        String b = "b";
        String c = "c";
        a = b = c;
        // a = c, b = c, c = c
        System.out.println("a = " + a + ", b = " + b + ", c = " + c);
    }

    /**
     * 赋值(栈顶运算)
     */
    private static void assignment2() {
        int a = 1;
        int b = a + (++a) + (++a);
        System.out.println("a = " + a + " , b = " + b);
    }
}
