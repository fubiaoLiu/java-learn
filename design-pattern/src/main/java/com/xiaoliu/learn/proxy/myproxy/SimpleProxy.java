package com.xiaoliu.learn.proxy.myproxy;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

/**
 * @description: 模拟JDK动态代理(简单版本): 单接口、没有import
 * .java -> .class -> Object
 * @author: FuBiaoLiu
 * @date: 2019/11/6
 */
public class SimpleProxy {
    private static final String TAB = "\t";
    private static final String LINE = "\n";
    private static final String BLANK = " ";
    private static final String RETURN = "return ";

    /**
     * 创建代理对象
     *
     * @param target 目标对象
     * @return
     */
    public static Object newProxyInstance(Object target) {
        Class targetClass = target.getClass();
        String proxyName = "$SimpleProxy" + ProxyUtil.generateID();
        String proxyObj = generateCode(targetClass, proxyName);
        String fileName = ProxyUtil.generateJavaFile(targetClass.getPackage().getName(), proxyName, proxyObj);
        ProxyUtil.compilerJava(fileName);
        return ProxyUtil.loadClass(targetClass.getPackage().getName() + "." + proxyName, targetClass.getInterfaces()[0], target);
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
                    .append("System.out.println(\"Simple Proxy Logic\");");
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
}
