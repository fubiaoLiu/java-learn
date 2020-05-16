package com.xiaoliu.learn.framework.singleton;

/**
 * @description: 饿汉单例
 * @author: liufb
 * @create: 2020/5/16 18:58
 **/
public class HungrySingletonPattern {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        instance.executor();
    }

    public static class Singleton {
        private static Singleton instance = new Singleton();

        // 私有的构造函数，只有自己能创建它的实力对象
        private Singleton() {
        }

        public static Singleton getInstance() {
            return instance;
        }

        public void executor() {
            System.out.println("执行业务逻辑");
        }
    }
}
