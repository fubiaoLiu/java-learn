package com.xiaoliu.learn.framework.proxy;

/**
 * @description: 使用代理模式
 * @author: liufb
 * @create: 2020/5/17 18:45
 **/
public class ProxyPattern {
    public static void main(String[] args) {
        Subject target = new ConcreteSubject();
        Subject proxy = new Proxy(target);
        proxy.executor();
    }
    public interface Subject {
        void executor();
    }

    public static class ConcreteSubject implements Subject {
        @Override
        public void executor() {
            System.out.println("执行功能逻辑");
        }
    }

    public static class Proxy implements Subject {
        private Subject target;

        public Proxy(Subject target) {
            this.target = target;
        }

        @Override
        public void executor() {
            System.out.println("执行代理逻辑");
            target.executor();
        }
    }
}
