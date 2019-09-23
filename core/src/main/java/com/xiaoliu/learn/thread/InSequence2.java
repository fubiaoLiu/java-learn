package com.xiaoliu.learn.thread;

/**
 * @description: 按顺序执行线程：共享对象加可见变量
 * @author: FuBiaoLiu
 * @date: 2019/9/23
 */
public class InSequence2 {
    public static void main(String[] args) throws InterruptedException {
        Method method = new Method();
        Thread thread1 = new Thread(() -> method.method1());
        Thread thread2 = new Thread(() -> method.method2());
        Thread thread3 = new Thread(() -> method.method3());
        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class Method {
        private static volatile int orderNum = 1;

        public synchronized void method1() {
            try {
                while (orderNum != 1) {
                    wait();
                }
                System.out.println("Method1 Running!");
                orderNum = 2;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void method2() {
            try {
                while (orderNum != 2) {
                    wait();
                }
                System.out.println("Method2 Running!");
                orderNum = 3;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void method3() {
            try {
                while (orderNum != 3) {
                    wait();
                }
                System.out.println("Method3 Running!");
                orderNum = 1;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
