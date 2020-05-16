package com.xiaoliu.learn.framework.factory;

/**
 * @description: 不用简单工厂模式
 * 缺点：直接面向一个类来编程，用new来创建类的实例，如果后面要更换一个类，就必须在创建这些类的地方全都要修改。
 * 代码可维护性和可扩展性极差。
 * @author: liufb
 * @create: 2020/5/16 14:35
 **/
public class WithoutSimpleFactoryPattern {
    public static void main(String[] args) {
        Product product = new Product();
        System.out.println(product);
    }

    public static class Product {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
