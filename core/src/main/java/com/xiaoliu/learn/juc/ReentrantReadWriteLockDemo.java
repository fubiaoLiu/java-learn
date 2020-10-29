package com.xiaoliu.learn.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 可重入读写锁
 * @author: liufb
 * @create: 2020/9/21 9:05
 **/
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        lock.readLock().unlock();

        lock.writeLock().lock();
        lock.writeLock().unlock();
    }
}
