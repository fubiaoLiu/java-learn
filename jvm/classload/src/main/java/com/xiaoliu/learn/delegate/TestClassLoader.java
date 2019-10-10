package com.xiaoliu.learn.delegate;

import java.io.*;

/**
 * @description: 遵循双亲委派
 * @author: FuBiaoLiu
 * @date: 2019/9/25
 */
public class TestClassLoader extends ClassLoader {
    private String name;

    public TestClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] data = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             InputStream is = new FileInputStream(new File("E:\\LearnDemo\\Test.class"))) {
            int c;
            while (-1 != (c = is.read())) {
                baos.write(c);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(data.length);
        return this.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) {
        TestClassLoader loader = new TestClassLoader(TestClassLoader.class.getClassLoader(), "TestClassLoader");
        Class clazz;
        try {
            clazz = loader.loadClass("com.xiaoliu.learn.delegate.Test");
            Object object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
