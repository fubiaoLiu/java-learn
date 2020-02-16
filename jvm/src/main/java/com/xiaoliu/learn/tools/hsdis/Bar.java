package com.xiaoliu.learn.tools.hsdis;

/**
 * @description: 循环调用sum方法，触发JIT，HSDIS在控制台输出反汇编代码
 * 启动参数：-XX:+UnlockDiagnosticVMOptions # 解锁虚拟机诊断操作
 *         -XX:+PrintAssembly # 输出反汇编内容
 *         -XX:CompileCommand=compileonly,*Bar.sum # 仅编译Bar#sum方法
 *         -XX:CompileCommand=dontinline,*Bar.sum # 禁止Bar#sum方法内联，此选项应该对本程序演示无影响
 * @author: FuBiaoLiu
 * @date: 2019/8/20
 */
public class Bar {
    int a = 1;
    static int b = 2;

    public int sum(int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 10000; i++) {
            new Bar().sum(3);
        }
    }
}
