package com.xiaoliu.learn.classload.delegate;

public class Test {
    public Test() {
        System.out.println(this.getClass().getClassLoader().toString());
    }
}
