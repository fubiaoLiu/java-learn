package com.xiaoliu.learn.framework.singleton;

/**
 * @description: 线程安全的懒汉单例(Double Check) -
 * 其实还是存在问题的，真正安全的是InnerClassLazySingletonPattern(内部类)
 * @author: liufb
 * @create: 2020/5/16 18:58
 **/
public class SafeLazySingletonPattern {
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
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }

        public void executor() {
            System.out.println("执行业务逻辑");
        }
    }
}
