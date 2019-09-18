package com.xiaoliu.learn.search;

/**
 * @description: 二分查找
 * 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置
 * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
 * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
 * @author: FuBiaoLiu
 * @date: 2019/9/16
 */
public class BinarySearch {
    public static void main(String[] args) {
        /*int[] array = {1, 2, 9, 8, 4, 6, 2, 5, 3};
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }*/
        int[] array = {1, 2, 3, 5, 5, 6, 7, 8, 9, 11, 12};
        int index = search(array, 8);
        System.out.println(index);
    }

    /**
     * 查找方法
     *
     * @param array 有序数组
     * @param a     待查关键字
     * @return
     */
    private static int search(int[] array, int a) {
        int lo = 0;
        int hi = array.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (array[mid] == a) {
                // 中间位置
                return mid + 1;
            } else if (array[mid] < a) {
                // 向右查找
                lo = mid + 1;
            } else {
                // 向左查找
                hi = mid - 1;
            }
        }
        return -1;
    }
}
