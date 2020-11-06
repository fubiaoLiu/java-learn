package com.xiaoliu.learn.juc;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 测试notify唤醒的顺序
 * 在JDK的代码注释中说：notify唤醒的顺序是随机的，依赖具体的JVM实现
 * 而在主流的hotspot虚拟机中，notify会从wait set中按照"先进先出"的顺序唤醒，即FIFO（先进先出）。
 * notifyAll唤起的线程，默认情况是最后进入的会先被唤起来，即LIFO（后进先出）。
 * notifyAll的实现是调用notify的线程在退出其同步块的时候唤醒起最后一个进入wait状态的线程；
 * 然后这个线程退出同步块的时候继续唤醒其倒数第二个进入wait状态的线程，依次类推。
 *
 * @author Fubiao.Liu
 * @since 2020/11/3 10:58
 **/
public class NotifyDemo {
    /**
     * 等待列表, 用来记录等待的顺序
     */
    private static List<String> waitList = new LinkedList<>();
    /**
     * 唤醒列表, 用来唤醒的顺序
     */
    private static List<String> notifyList = new LinkedList<>();

    private static final Object LOCK = new Object();


    public static void main(String[] args) throws InterruptedException {

        // 创建50个线程
        for (int i = 0; i < 50; i++) {
            String threadName = Integer.toString(i);
            new Thread(() -> {
                synchronized (LOCK) {
                    String cthreadName = Thread.currentThread().getName();
                    System.out.println("线程 [" + cthreadName + "] 正在等待.");
                    waitList.add(cthreadName);
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程 [" + cthreadName + "] 被唤醒了.");
                    notifyList.add(cthreadName);
                }
            }, threadName).start();

            TimeUnit.MILLISECONDS.sleep(50);
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 0; i < 50; i++) {
            synchronized (LOCK) {
                LOCK.notify();
                // sleep代码不能放在这里，因为要退出synchronzied代码块之后才会释放锁
                // 当前线程释放锁后可能会再次获取锁，会再次唤醒wait线程，多个wait线程被唤醒后无法保证获取锁的顺序
                // TimeUnit.MILLISECONDS.sleep(10);
                /*if (i == 0) {
                    createThreads();
                }*/
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("wait顺序:" + waitList.toString());
        System.out.println("notify顺序:" + notifyList.toString());
    }

    /**
     * 在已有线程wait时，新进线程尝试获取锁，wait线程和新线程获取锁的顺序不确定
     */
    private static void createThreads() {
        for (int i = 0; i < 5; i++) {
            String threadName = "后" + i;
            new Thread(() -> {
                String cthreadName = Thread.currentThread().getName();
                try {
                    int sleepTime = new Random().nextInt(200);
                    Thread.sleep(sleepTime);
                    System.out.println("线程 [" + cthreadName + "] 休眠" + sleepTime + "ms.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    System.out.println("线程 [" + cthreadName + "] 获取锁.");
                    notifyList.add(cthreadName);
                }
            }, threadName).start();
        }
    }
}
