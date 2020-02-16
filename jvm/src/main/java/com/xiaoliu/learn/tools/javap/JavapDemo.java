package com.xiaoliu.learn.tools.javap;

/**
 * @description: 模拟javap命令对不同作用域的变量输出效果
 * @author: FuBiaoLiu
 * @date: 2019/8/20
 */
public class JavapDemo {
    public String a;
    protected String b;
    private String c;
    public static String d;
    protected static String e;
    private static String f;
    public static final String g = "g";
    protected static final String h = "h";
    private static final String i = "i";

    public void a() {
        System.out.println("a");
    }

    protected void b() {
        System.out.println("b");
    }

    private void c() {
        System.out.println("c");
    }
}
