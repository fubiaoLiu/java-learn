package com.xiaoliu.learn.framework.mediator;

/**
 * @description: 不用中介者模式
 * 假如模块A要调用模块B、模块C中的某个方法、模块B要调用模块A、模块C中的某个方法、模块C要调用模块A、模块B中的某个方法,
 * 不用中介者模式，每个模块都要关注其他模块的很多内容，互相之间高度耦合。
 * 如果需要修改代码，可能会影响到其他模块。
 * @author: liufb
 * @create: 2020/5/17 18:27
 **/
public class WithoutMediatorPattern {
    public static void main(String[] args) {
        ModuleA moduleA = new ModuleA();
        ModuleB moduleB = new ModuleB();
        ModuleC moduleC = new ModuleC();
        moduleA.executor();
        moduleB.executor();
        moduleC.executor();
    }

    public static class ModuleA {
        public void executor() {
            ModuleB moduleB = new ModuleB();
            ModuleC moduleC = new ModuleC();
            moduleB.executor("模块A");
            moduleC.executor("模块A");
        }

        public void executor(String invoker) {
            System.out.println(invoker + "调用模块A的功能");
        }
    }

    public static class ModuleB {
        public void executor() {
            ModuleA moduleA = new ModuleA();
            ModuleC moduleC = new ModuleC();
            moduleA.executor("模块B");
            moduleC.executor("模块B");
        }

        public void executor(String invoker) {
            System.out.println(invoker + "调用模块B的功能");
        }
    }

    public static class ModuleC {
        public void executor() {
            ModuleA moduleA = new ModuleA();
            ModuleB moduleB = new ModuleB();
            moduleA.executor("模块C");
            moduleB.executor("模块C");
        }

        public void executor(String invoker) {
            System.out.println(invoker + "调用模块C的功能");
        }
    }
}
