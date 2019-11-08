package com.xiaoliu.learn.proxy.myproxy;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

/**
 * @description: 模拟JDK动态代理
 * .java -> .class -> Object
 * @author: FuBiaoLiu
 * @date: 2019/11/6
 */
public class MyProxy {
    private static final String TAB = "\t";
    private static final String LINE = "\n";
    private static final String BLANK = " ";
    private static final String SEMICOLON = ";";
    private static final String COMMA = ",";
    private static final String RETURN = "return ";
    private static final String IMPORT = "import ";
    private static final String OVERRIDE = "@Override";
    private static final String PROXY_PACKAGE = "com.xiaoliu.learn.proxy.obj";

    /**
     * 创建代理对象
     *
     * @param interfaces 目标接口
     * @param handler    代理逻辑对象
     * @return
     */
    public static Object newProxyInstance(Class[] interfaces, CustomInvocationHandler handler) {
        String proxyName = "$Proxy" + ProxyUtil.generateID();
        String proxyObj = generateCode(interfaces, proxyName);
        String fileName = ProxyUtil.generateJavaFile(PROXY_PACKAGE, proxyName, proxyObj);
        ProxyUtil.compilerJava(fileName);
        return ProxyUtil.loadClass(PROXY_PACKAGE + "." + proxyName, handler);
    }

    /**
     * 生成代码
     *
     * @param interfaces 目标对象
     * @param proxyName  代理对象名
     * @return
     */
    private static String generateCode(Class[] interfaces, String proxyName) {
        if (interfaces == null || interfaces.length == 0) {
            throw new RuntimeException("目标对象不能为空！");
        }
        StringJoiner importPkg = new StringJoiner(SEMICOLON + LINE + IMPORT, LINE + IMPORT, SEMICOLON + LINE);
        StringJoiner implInterface = new StringJoiner(COMMA, " implements ", "");
        StringBuilder proxyMethod = new StringBuilder();
        for (Class target : interfaces) {
            importPkg.add(target.getName());
            implInterface.add(target.getSimpleName());

            Method[] methods = target.getMethods();
            for (Method method : methods) {
                proxyMethod.append(LINE).append(TAB).append(OVERRIDE).append(LINE).append(TAB)
                        .append("public ").append(method.getReturnType().getName()).append(BLANK).append(method.getName())
                        .append("(");
                Parameter[] param = method.getParameters();
                boolean hasParam = param != null && param.length > 0;
                boolean hasReturn = !"void".equals(method.getReturnType().getSimpleName());
                StringJoiner targetParamType = new StringJoiner(COMMA);
                StringJoiner targetParam = new StringJoiner(COMMA);
                if (hasParam) {
                    StringJoiner proxyParam = new StringJoiner(COMMA);
                    for (int i = 0; i < param.length; i++) {
                        Parameter parameter = param[i];
                        proxyParam.add(parameter.getType().getName() + " p" + i);
                        targetParamType.add(parameter.getType().getName() + ".class");
                        targetParam.add("p" + i);
                    }
                    proxyMethod.append(proxyParam);
                }
                proxyMethod.append("){").append(LINE + TAB + TAB).append("try {")
                        .append(LINE + TAB + TAB + TAB)
                        .append("Method method = Class.forName(\"").append(target.getName())
                        .append("\").getMethod(\"").append(method.getName()).append("\"");
                if (hasParam) {
                    proxyMethod.append(COMMA).append(targetParamType);
                }
                proxyMethod.append(");")
                        .append(LINE + TAB + TAB + TAB)
                        .append("Object[] args = new Object[]{").append(targetParam).append("};")
                        .append(LINE + TAB + TAB + TAB);
                if (hasReturn) {
                    proxyMethod.append(RETURN).append("(").append(method.getReturnType().getName()).append(")");
                }
                proxyMethod.append("handler.invoke(method, args);")
                        .append(LINE + TAB + TAB).append("} catch (Throwable e) {")
                        .append(LINE + TAB + TAB + TAB).append("e.printStackTrace();")
                        .append(LINE + TAB + TAB).append("}");
                if (hasReturn) {
                    proxyMethod.append(LINE + TAB + TAB).append(RETURN + "null;");
                }
                proxyMethod.append(LINE + TAB).append("}").append(LINE);
            }
        }
        importPkg.add(Method.class.getName()).add(CustomInvocationHandler.class.getName());

        String proxyPackage = "package " + PROXY_PACKAGE + SEMICOLON;
        String classDef = "public class " + proxyName + implInterface;
        String handle = LINE + TAB + "private CustomInvocationHandler handler;";
        String constructor = LINE + TAB + "public " + proxyName + "(CustomInvocationHandler handler){"
                + LINE + TAB + TAB + "this.handler = handler;" + LINE + TAB + "}";

        return proxyPackage + LINE + importPkg + LINE + classDef + "{" + handle + constructor + proxyMethod + "}";
    }
}
