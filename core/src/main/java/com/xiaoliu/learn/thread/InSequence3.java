package com.xiaoliu.learn.thread;

import java.util.Random;

/**
 * @description: 按顺序执行线程：线程内执行join()
 * @author: FuBiaoLiu
 * @date: 2019/9/23
 */
public class InSequence3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2(thread1);
        Thread thread3 = new Thread3(thread2);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 Running!");
        }
    }

    static class Thread2 extends Thread {
        private Thread thread;

        public Thread2(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 Running!");
        }
    }

    static class Thread3 extends Thread {
        private Thread thread;

        public Thread3(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread3 Running!");
        }
    }

}
