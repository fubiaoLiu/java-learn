package com.xiaoliu.learn.sort;

/**
 * @description: 冒泡排序
 * @author: FuBiaoLiu
 * @date: 2019/9/16
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {1, 2, 9, 8, 4, 6, 2, 5, 3};
        sort(array, array.length);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void sort(int[] a, int n) {
        int i, j;
        for (i = 0; i < n; i++) {
            // 表示 n 次排序过程
            for (j = 1; j < n - i; j++) {
                // 前面的数字大于后面的数字就交换
                if (a[j - 1] > a[j]) {
                    // 交换 a[j-1]和 a[j]
                    int temp;
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
