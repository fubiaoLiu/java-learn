package com.xiaoliu.learn.proxy.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringJoiner;

/**
 * @description: 模拟JDK动态代理(简单版本): 单接口、没有import
 * .java -> .class -> Object
 * @author: FuBiaoLiu
 * @date: 2019/11/6
 */
public class MyProxy {
    private static final String TAB = "\t";
    private static final String LINE = "\n";
    private static final String BLANK = " ";
    private static final String RETURN = "return ";
    private static final String ROOT_PATH = "E:\\\\";

    public static Object newProxyInstance(Object target) {
        Class targetClass = target.getClass();
        String proxyName = "$Proxy";
        String proxyObj = generateCode(targetClass, proxyName);
        String fileName = generateJavaFile(targetClass.getPackage().getName(), proxyName, proxyObj);
        compilerJava(fileName);
        return loadClass(targetClass.getPackage().getName() + "." + proxyName, targetClass.getInterfaces()[0], target);
//        return null;
    }

    /**
     * 生成代码
     *
     * @param targetClass 目标对象
     * @param proxyName   代理对象名
     * @return
     */
    private static String generateCode(Class targetClass, String proxyName) {
        // 只对目标对象的第一个接口做代理
        Class targetInterface = targetClass.getInterfaces()[0];
        String targetInterfaceName = targetInterface.getSimpleName();
        String proxyPackage = targetClass.getPackage() + ";";
        String classDef = "public class " + proxyName + " implements " + targetInterfaceName;
        String targetObj = LINE + TAB + "private " + targetInterfaceName + " target;";
        String constructor = LINE + TAB + "public " + proxyName + "(" + targetInterfaceName + " target){"
                + LINE + TAB + TAB + "this.target = target;" + LINE + TAB + "}";

        Method[] methods = targetInterface.getMethods();
        StringBuilder proxyMethod = new StringBuilder();
        for (Method method : methods) {
            proxyMethod.append(LINE).append(TAB).append("@Override").append(LINE).append(TAB)
                    .append("public ").append(method.getReturnType().getName()).append(BLANK).append(method.getName())
                    .append("(");
            Parameter[] param = method.getParameters();
            StringJoiner targetParam = new StringJoiner(",");
            if (param != null && param.length > 0) {
                StringJoiner proxyParam = new StringJoiner(",");
                for (int i = 0; i < param.length; i++) {
                    Parameter parameter = param[i];
                    proxyParam.add(parameter.getType().getName() + " p" + i);
                    targetParam.add("p" + i);
                }
                proxyMethod.append(proxyParam);
            }
            proxyMethod.append("){").append(LINE).append(TAB).append(TAB)
                    .append("System.out.println(\"My Proxy Logic\");");
            proxyMethod.append(LINE).append(TAB).append(TAB);
            if (!"void".equals(method.getReturnType().getSimpleName())) {
                proxyMethod.append(RETURN);
            }
            proxyMethod.append("target.").append(method.getName())
                    .append("(").append(targetParam).append(");")
                    .append(LINE).append(TAB).append("}").append(LINE);

        }

        return proxyPackage + LINE + classDef + "{" + targetObj + constructor + proxyMethod + "}";
    }

    /**
     * 生成代码
     *
     * @param proxyPackage 代理类包名
     * @param proxyClass   代理类名
     * @param code         代码
     */
    private static String generateJavaFile(String proxyPackage, String proxyClass, String code) {
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
     * 编译文件
     *
     * @param fileName 文件路径
     */
    private static void compilerJava(String fileName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
            Iterable units = fileManager.getJavaFileObjects(fileName);
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
            t.call();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object loadClass(String className, Class targetClass, Object target) {
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
}
