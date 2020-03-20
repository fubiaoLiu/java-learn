package com.xiaoliu.learn.proxy.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @description: 代理工具类
 * @author: FuBiaoLiu
 * @date: 2019/11/8
 */
public class ProxyUtil {
    private static final String ROOT_PATH = "E:\\\\";
    private static byte[] lock = new byte[0];
    /**
     * 位数，默认是8位
     */
    private static final long W = 100000000L;

    /**
     * 编译文件
     *
     * @param fileName 文件路径
     */
    public static void compilerJava(String fileName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
            Iterable units = fileManager.getJavaFileObjects(fileName);
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
            t.call();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成代码
     *
     * @param proxyPackage 代理类包名
     * @param proxyClass   代理类名
     * @param code         代码
     */
    public static String generateJavaFile(String proxyPackage, String proxyClass, String code) {
        proxyPackage = proxyPackage.replace(".", "\\");
        String filePath = ROOT_PATH + proxyPackage + "\\";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = filePath + proxyClass + ".java";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(code);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 加载类
     *
     * @param className 代理类名称
     * @param handler   handler
     * @return
     */
    public static Object loadClass(String className, CustomInvocationHandler handler) {
        try {
            URL[] urls = new URL[]{new URL("file:" + ROOT_PATH)};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass(className);
            Constructor constructor = clazz.getConstructor(CustomInvocationHandler.class);
            return constructor.newInstance(handler);
        } catch (MalformedURLException e) {
            System.out.println("URL load failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class load failed!");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Get method failed!");
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException e) {
            System.out.println("Create instance failed!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载类
     *
     * @param className   代理类全名
     * @param targetClass 目标类(接口)
     * @param target      目标对象
     * @return
     */
    public static Object loadClass(String className, Class targetClass, Object target) {
        try {
            URL[] urls = new URL[]{new URL("file:" + ROOT_PATH)};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass(className);
            Constructor constructor = clazz.getConstructor(targetClass);
            return constructor.newInstance(target);
        } catch (MalformedURLException e) {
            System.out.println("URL load failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class load failed!");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Get method failed!");
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException e) {
            System.out.println("Create instance failed!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据时间戳和随机数生成ID
     *
     * @return
     */
    public static String generateID() {
        long r;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * W);
        }
        return String.valueOf(System.currentTimeMillis()).substring(10) + String.valueOf(r).substring(1);
    }
}
