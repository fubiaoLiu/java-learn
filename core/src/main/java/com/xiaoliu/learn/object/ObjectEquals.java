package com.xiaoliu.learn.object;

/**
 * @description: Object 类的 equals() 方法
 * @author: FuBiaoLiu
 * @date: 2019/9/22
 */
public class ObjectEquals {
    private int val;

    public ObjectEquals(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        ObjectEquals oe1 = new ObjectEquals(1);
        ObjectEquals oe2 = new ObjectEquals(1);
        System.out.println(oe1 == oe2);
        System.out.println(oe1.equals(oe2));
        // false
        // false
    }
}
