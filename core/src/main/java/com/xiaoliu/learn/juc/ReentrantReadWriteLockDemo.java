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

        // EXCLUSIVE_MASK:      (1 << 16) - 1
        // 1:                   0000 0000 0000 0000 0000 0000 0000 0001
        // 1 << 16:             0000 0000 0000 0001 0000 0000 0000 0000
        // (1 << 16) - 1:       0000 0000 0000 0000 1111 1111 1111 1111
        // c & EXCLUSIVE_MASK:  取低16位
        // c >>> SHARED_SHIFT:  取高16位
        System.out.println( 5 & ((1 << 16) - 1));
    }
}
