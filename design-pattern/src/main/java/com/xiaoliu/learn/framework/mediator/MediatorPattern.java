package com.xiaoliu.learn.framework.mediator;

/**
 * @description: 使用中介者模式
 * 假如模块A要调用模块B、模块C中的某个方法、模块B要调用模块A、模块C中的某个方法、模块C要调用模块A、模块B中的某个方法,
 * 使用中介者模式，将具体与其他模块的交互都封装在中介者中，各模块之间不再有任何耦合。
 * @author: liufb
 * @create: 2020/5/17 18:27
 **/
public class MediatorPattern {
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        ModuleA moduleA = new ModuleA(mediator);
        ModuleB moduleB = new ModuleB(mediator);
        ModuleC moduleC = new ModuleC(mediator);
        moduleA.executor();
        moduleB.executor();
        moduleC.executor();
    }

    public static class ModuleA {
        private Mediator mediator;

        public ModuleA(Mediator mediator) {
            this.mediator = mediator;
            this.mediator.moduleA = this;
        }

        public void executor() {
            mediator.moduleAInvoke();
        }

        public void executor(String invoker) {
            System.out.println(invoker + "调用模块A的功能");
        }
    }

    public static class ModuleB {
        private Mediator mediator;

        public ModuleB(Mediator mediator) {
            this.mediator = mediator;
            this.mediator.moduleB = this;
        }

        public void executor() {
            mediator.moduleBInvoke();
        }

        public void executor(String invoker) {
            System.out.println(invoker + "调用模块B的功能");
        }
    }

    public static class ModuleC {
        private Mediator mediator;

        public ModuleC(Mediator mediator) {
            this.mediator = mediator;
            this.mediator.moduleC = this;
        }

        public void executor() {
            mediator.moduleCInvoke();
        }

        public void executor(String invoker) {
            System.out.println(invoker + "调用模块C的功能");
        }
    }

    public static class Mediator {
        private ModuleA moduleA;
        private ModuleB moduleB;
        private ModuleC moduleC;

        public ModuleA getModuleA() {
            return moduleA;
        }

        public void setModuleA(ModuleA moduleA) {
            this.moduleA = moduleA;
        }

        public ModuleB getModuleB() {
            return moduleB;
        }

        public void setModuleB(ModuleB moduleB) {
            this.moduleB = moduleB;
        }

        public ModuleC getModuleC() {
            return moduleC;
        }

        public void setModuleC(ModuleC moduleC) {
            this.moduleC = moduleC;
        }

        public void moduleAInvoke() {
            moduleB.executor("模块A通知中介者");
            moduleC.executor("模块A通知中介者");
        }

        public void moduleBInvoke() {
            moduleB.executor("模块B通知中介者");
            moduleC.executor("模块B通知中介者");
        }

        public void moduleCInvoke() {
            moduleB.executor("模块C通知中介者");
            moduleC.executor("模块C通知中介者");
        }
    }
}
