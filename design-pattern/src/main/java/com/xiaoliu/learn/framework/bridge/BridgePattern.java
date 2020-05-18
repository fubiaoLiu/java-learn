package com.xiaoliu.learn.framework.bridge;

/**
 * @description: 使用桥接模式 - 在Java中无处不桥接，基于接口编程就是桥接模式，接口就是桥。
 * 调用方通过接口调用具体功能的实现类。
 * implementor是一个代码组件，包含了一个接口和一个实现类。
 * abstraction也是一个代码组件，包含了一个抽象类和一个子类。
 * abstraction调用implementor的时候，是基于Implementor接口来编程的。
 * @author: liufb
 * @create: 2020/5/18 9:20
 **/
public class BridgePattern {
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementor();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.executor();
    }

    public interface Implementor {
        void executor();
    }

    public static class ConcreteImplementor implements Implementor {
        @Override
        public void executor() {
            System.out.println("执行功能逻辑");
        }
    }

    public static abstract class Abstraction {
        protected Implementor implementor;

        public Abstraction(Implementor implementor) {
            this.implementor = implementor;
        }

        public abstract void executor();
    }

    public static class RefinedAbstraction extends Abstraction {
        public RefinedAbstraction(Implementor implementor) {
            super(implementor);
        }

        @Override
        public void executor() {
            implementor.executor();
        }
    }
}
