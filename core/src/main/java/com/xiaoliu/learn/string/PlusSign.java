package com.xiaoliu.learn.string;

/**
 * @description: String中使用"+"
 * @author: FuBiaoLiu
 * @date: 2019/8/27
 */
public class PlusSign {

    public static void main(String[] args) {
        func1();
    }

    /**
     * 包含值为null的字符串相加
     * 结果：nullabc
     */
    private static void func1() {
        String s1 = null;
        String s2 = "abc";
        System.out.println(s1 + s2);
    }
}
