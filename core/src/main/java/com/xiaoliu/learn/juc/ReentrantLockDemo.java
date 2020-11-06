package com.xiaoliu.learn.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 可重入锁
 * @author: liufb
 * @create: 2020/9/21 9:05
 **/
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            System.out.println("线程1加锁...");
            lock.lock();
            try {
                // lock.tryLock(1000, TimeUnit.MILLISECONDS);
                System.out.println("线程1阻塞等待...");
                condition.await();
                System.out.println("线程1被唤醒...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程1释放锁...");
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("线程2加锁...");
            lock.lock();
            try {
                System.out.println("线程2唤醒线程1...");
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程2释放锁...");
            }
        }).start();
    }


}
