package com.xiaoliu.learn.framework.singleton;

/**
 * @description: 线程不安全的懒汉单例
 * @author: liufb
 * @create: 2020/5/16 18:58
 **/
public class UnsafeLazySingletonPattern {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        instance.executor();
    }

    public static class Singleton {
        private static Singleton instance;

        private Singleton() {
        }

        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }

        public void executor() {
            System.out.println("执行业务逻辑");
        }
    }
}
