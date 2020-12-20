package com.xiaoliu.learn.bitoperation;

/**
 * 二进制输出
 *
 * @author Fubiao.Liu
 * @since 2020/11/24 15:56
 **/
public class BinaryPrint {
    public static void main(String[] args) {
        // 在计算机中，负数以其正值的补码形式表达。例：-1 等于 1 的补码
        //  1原码     : 0000 0000 0000 0000 0000 0000 0000 0001
        //  1反码     : 1111 1111 1111 1111 1111 1111 1111 1110
        //  1补码     : 1111 1111 1111 1111 1111 1111 1111 1111
        //

        // 536870912原码  : 0010 0000 0000 0000 0000 0000 0000 0000
        // 536870912反码  : 1101 1111 1111 1111 1111 1111 1111 1111
        // 536870912补码  : 1110 0000 0000 0000 0000 0000 0000 0000

        // 1 : 00000000000000000000000000000001
        printNumAndBinary(1);
        // 536870912 : 00100000000000000000000000000000
        printNumAndBinary(1 << 29);
        System.out.println("---------------------------------------------");
        // -1 : 11111111111111111111111111111111
        printNumAndBinary(-1);
        // -536870912 : 11100000000000000000000000000000
        printNumAndBinary(-1 << 29);
        // -536870912 : 11100000000000000000000000000000
        printNumAndBinary(-1 << 29 | 0);
        // -1073741824 : 11000000000000000000000000000000
        printNumAndBinary(-1 << 30);
        // -2147483648 : 10000000000000000000000000000000
        printNumAndBinary(-1 << 31);
        // -2147483647 : 10000000000000000000000000000001
        printNumAndBinary((-1 << 31) + 1);
        // 2147483647 : 01111111111111111111111111111111
        printNumAndBinary(2147483647);
        // -2147483648 : 10000000000000000000000000000000
        printNumAndBinary(2147483647 + 1);
    }

    private static void printNumAndBinary(int num) {
//        System.out.println(num + " : " + toBinaryString(num));
        System.out.println(num + " : " + Integer.toBinaryString(num));
    }

    private static String toBinaryString(int num) {
        if (num == 0) {
            return "" + 0;
        }
        StringBuilder result = new StringBuilder();
        // 左面0的个数
        int n = Integer.numberOfLeadingZeros(num);
        num <<= n;
        // System.out.println("num <<= n" + (num <<= n));
        for (int i = 0; i < 32 - n; ++i) {
            int x = (Integer.numberOfLeadingZeros(num) == 0) ? 1 : 0;
            result.append(x);
            num <<= 1;
        }
        return result.toString();
    }
}