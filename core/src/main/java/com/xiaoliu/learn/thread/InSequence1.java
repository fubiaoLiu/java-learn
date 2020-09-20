package com.xiaoliu.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 按顺序执行线程
 * @author: FuBiaoLiu
 * @date: 2019/9/23
 */
public class InSequence1 {
    public static void main(String[] args) throws InterruptedException {
        func1();
//        func2();
    }

    /**
     * 方法一：join
     *
     * @throws InterruptedException
     */
    private static void func1() throws InterruptedException {
        Thread thread1 = createThread("Thread1");
        Thread thread2 = createThread("Thread2");
        Thread thread3 = createThread("Thread3");

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }

    /**
     * 方法二：Excutors.newSingleThreadExecutor()
     *
     * @throws InterruptedException
     */
    private static void func2() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread thread1 = createThread("Thread1");
        Thread thread2 = createThread("Thread2");
        Thread thread3 = createThread("Thread3");

        executor.submit(thread1);
        executor.submit(thread2);
        executor.submit(thread3);
//        executor.execute(thread1);
//        executor.execute(thread2);
//        executor.execute(thread3);
        executor.shutdown();
    }

    /**
     * 根据线程名创建线程
     *
     * @param threadName
     * @return
     */
    private static Thread createThread(String threadName) {
        return new Thread(() -> System.out.println(threadName + " Running!"));
    }

}
