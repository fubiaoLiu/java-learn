package com.xiaoliu.learn.bitoperation;

/**
 * @description: 异或运算
 * @author: liufb
 * @create: 2020/6/18 13:49
 **/
public class XOR {
    public static void main(String[] args) {
        exchangeDigital();
    }

    /**
     * 两个数字交换
     */
    public static void exchangeDigital() {
        int a = 1;
        int b = 2;
        System.out.println("a = " + a + " , b = " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        // a = 1 ^ 2 = 01 ^ 10 = 11 = 3
        // b = 3 ^ 2 = 11 ^ 10 = 01 = 1
        // a = 3 ^ 1 = 11 ^ 01 = 10 = 2
        System.out.println("a = " + a + " , b = " + b);

        a = a + b - (b = a);
        System.out.println("a = " + a + " , b = " + b);
    }
}
