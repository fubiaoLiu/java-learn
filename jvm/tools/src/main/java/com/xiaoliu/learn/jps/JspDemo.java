package com.xiaoliu.learn.jps;

public class JspDemo {
    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println(1);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
