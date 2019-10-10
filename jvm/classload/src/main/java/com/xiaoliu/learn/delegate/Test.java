package com.xiaoliu.learn.delegate;

public class Test {
    public Test() {
        System.out.println(this.getClass().getClassLoader().toString());
    }
}
