package com.xiaoliu.learn.framework.singleton;

/**
 * @description: 内部类懒汉单例
 * 只要没有被使用，Holder就不会初始化，Singleton实例就不会创建。Java语言保证Holder类静态初始化的过程只会执行一次。
 * @author: liufb
 * @create: 2020/5/16 18:58
 **/
public class InnerClassLazySingletonPattern {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        instance.executor();
    }

    public static class Singleton {
        private Singleton() {
        }

        public static Singleton getInstance() {
            return Holder.instance;
        }

        public void executor() {
            System.out.println("执行业务逻辑");
        }

        public static class Holder {
            private static final Singleton instance = new Singleton();
        }
    }
}
