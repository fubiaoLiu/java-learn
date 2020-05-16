package com.xiaoliu.learn.framework.factory;

/**
 * @description: 使用简单工厂模式
 * 优点：此处如果要修改类的实现，只需要修改Factory类中的create方法就好了。
 * @author: liufb
 * @create: 2020/5/16 14:35
 **/
public class SimpleFactoryPattern {
    public static void main(String[] args) {
        Product product = ProductFactory.create();
        System.out.println(product);
    }

    public static class ProductFactory {
        public static Product create() {
            return new ProductImpl1();
        }
    }

    public interface Product {
        void executor();
    }

    public static class ProductImpl1 implements Product {
        @Override
        public void executor() {
            System.out.println("产品1的功能");
        }
    }

    public static class ProductImpl2 implements Product {
        @Override
        public void executor() {
            System.out.println("产品2的功能");
        }
    }

    public static class ProductImpl3 implements Product {
        @Override
        public void executor() {
            System.out.println("产品3的功能");
        }
    }
}
