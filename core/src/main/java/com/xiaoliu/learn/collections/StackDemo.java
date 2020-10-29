package com.xiaoliu.learn.collections;


import java.util.Stack;

/**
 * @description: 栈源码查看
 * @author: liufb
 * @create: 2020/9/21 9:10
 **/
public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < 10; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
