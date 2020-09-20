package com.xiaoliu.learn.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("线程1加锁...");
                try {
                    System.out.println("线程1阻塞等待...");
                    condition.await();
                    System.out.println("线程1被唤醒...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1释放锁...");
                lock.unlock();
            }
        }.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("线程2加锁...");
                System.out.println("线程2唤醒线程1...");
                condition.signal();
                System.out.println("线程2释放锁...");
                lock.unlock();
            }
        }.start();
    }


}
