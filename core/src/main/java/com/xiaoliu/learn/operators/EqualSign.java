package com.xiaoliu.learn.operators;

/**
 * @description: 等号
 * @author: FuBiaoLiu
 * @date: 2019/9/12
 */
public class EqualSign {
    public static void main(String[] args) {
        assignment();
    }

    /**
     * 赋值
     */
    private static void assignment() {
        String a = "a";
        String b = "b";
        String c = "c";
        a = b = c;
        // a=c;b=c;c=c
        System.out.println("a=" + a + ";b=" + b + ";c=" + c);
    }
}
