package com.xiaoliu.learn.framework.decorator;

/**
 * @description: 使用装饰模式 - 主要用于对目标对象进行装饰、增强
 * Spring AOP就是经典的装饰模式，通过动态代理对目标类进行增强。
 * 与代理模式的代码实现很相似，不同的是思想：
 * 代理模式主要思想是代理，特别是对远程服务进行代理，比如SpringCloud、Dubbo中对远程接口的调用都使用代理。
 * 装饰模式主要思想是对目标对象进行修饰、功能增强，比如在枪械上安装瞄准镜、枪口都属于对功能增强。
 * @author: liufb
 * @create: 2020/5/18 9:26
 **/
public class DecoratorPattern {
    public static void main(String[] args) {
        Component target = new ConcreteComponent();
        Component decorator = new Decorator(target);
        decorator.executor();
    }
    public interface Component {
        void executor();
    }

    public static class ConcreteComponent implements Component {
        @Override
        public void executor() {
            System.out.println("执行目标功能逻辑");
        }
    }

    public static class Decorator implements Component {
        private Component component;

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void executor() {
            System.out.println("对目标功能进行增强");
            component.executor();
        }
    }


}
