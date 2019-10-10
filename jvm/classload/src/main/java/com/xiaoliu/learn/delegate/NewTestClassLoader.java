package com.xiaoliu.learn.delegate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @description: 打破双亲委派
 * @author: FuBiaoLiu
 * @date: 2019/9/25
 */
public class NewTestClassLoader extends ClassLoader {
    private String name;

    public NewTestClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        ClassLoader system = getSystemClassLoader();
        try {
            clazz = system.loadClass(name);
        } catch (Exception e) {
            // ignore
        }
        if (clazz != null) {
            return clazz;
        }
        clazz = findClass(name);
        return clazz;
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] data = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             InputStream is = new FileInputStream(new File("E:\\GitProjects\\java-learn\\jvm\\classload\\target\\classes\\com\\xiaoliu\\learn\\delegate\\Test.class"))) {
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
        NewTestClassLoader loader = new NewTestClassLoader(NewTestClassLoader.class.getClassLoader(), "NewTestClassLoader");
        Class clazz;
        try {
            clazz = loader.loadClass("com.xiaoliu.learn.delegate.Test");
            Object object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
