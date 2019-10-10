package com.xiaoliu.learn.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 能通过反射获取泛型信息的场景：成员变量的泛型、方法参数的泛型、方法返回值的泛型
 * 不能通过反射获取泛型信息的场景：类或接口声明的泛型、局部变量的泛型
 * @author: FuBiaoLiu
 * @date: 2019/9/25
 */
@Data
public class MyClass<T> {
    private List<String> list1 = new ArrayList<>();
    private List<T> list2 = new ArrayList<>();

    public void test(List<T> ls) {

    }

    public static void main(String[] args) throws Exception {
//        getMethodReturnType();
        getGenericFieldTypes();
//        getMethodParameterTypes();
    }

    /**
     * 获取方法返回值的泛型类型信息
     *
     * @throws Exception
     */
    public static void getMethodReturnType() throws Exception {
        Method method = MyClass.class.getMethod("getList", null);
        System.out.println(method.getReturnType());
        Type returnType = method.getGenericReturnType();
        System.out.println(returnType);
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                Class typeArgClass = (Class) typeArgument;
                System.out.println("泛型类型：" + typeArgClass);
            }
        }
    }

    /**
     * 获取成员变量的泛型类型信息
     *
     * @throws Exception
     */
    public static void getGenericFieldTypes() throws Exception {
        Field field = MyClass.class.getField("list1");
        Type genericsFieldType = field.getGenericType();
        if (genericsFieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericsFieldType;
            Type[] fieldArgTypes = parameterizedType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("泛型字段的类型：" + fieldArgClass);
            }
        }
    }

    /**
     * 获取方法参数的泛型类型信息
     */
    public static void getMethodParameterTypes() throws Exception {
        Method method = MyClass.class.getMethod("setList", List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericType : genericParameterTypes) {
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type type : types) {
                    Class realType = (Class) type;
                    System.out.println("方法参数的类型：" + realType);
                }
            }
        }
    }
}
