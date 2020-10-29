package com.xiaoliu.learn.collections.customize;

import java.util.Iterator;

/**
 * @description: {@link MyArrayList}测试
 * @author: liufb
 * @create: 2020/9/22 10:10
 **/
public class MyArrayListDemo {
    public static void main(String[] args) {
        // base();
        iterator();
    }

    /**
     * 迭代器测试
     */
    private static void iterator() {
        MyList<Integer> list = new MyArrayList<>();
        printInfo(list, "start");
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println();
        System.out.println("--------------init-------------");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
            // list.remove(list.size() - 1);
        }
    }

    /**
     * 基础方法测试
     */
    private static void base() {
        MyList<Integer> list = new MyArrayList<>();
        printInfo(list, "start");
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        printInfo(list, "init");
        for (int i = 0; i < 5; i++) {
            list.remove(i);
        }
        printInfo(list, "remove");
        System.out.println("indexOf(10):" + list.indexOf(10));
        System.out.println("contains(11):" + list.contains(11));
        list.set(0, 100);
        list.add(1, 200);
        printInfo(list, "set and add");
        list.clear();
        printInfo(list, "clear");
    }

    /**
     * 输出list信息
     *
     * @param list  list
     * @param title 打印的标题
     */
    private static void printInfo(MyList<Integer> list, String title) {
        System.out.println();
        System.out.println("--------------" + title + "-------------");
        System.out.println("size:" + list.size());
        System.out.println("isEmpty:" + list.isEmpty());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(",");
            } else {
                System.out.println();
            }
        }
    }
}
