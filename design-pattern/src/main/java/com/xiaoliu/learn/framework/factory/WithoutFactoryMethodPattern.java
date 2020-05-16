package com.xiaoliu.learn.framework.factory;

/**
 * @description: 不用工厂方法模式
 * 多个工厂，生成不同产品时有一些通用的逻辑
 * @author: liufb
 * @create: 2020/5/16 14:35
 **/
public class WithoutFactoryMethodPattern {
    public static void main(String[] args) {
        ProductA productA = ProductAFactory.create();
        ProductB productB = ProductBFactory.create();
        ProductC productC = ProductCFactory.create();
        productA.executor();
        productB.executor();
        productC.executor();
    }

    public static class ProductA {
        public void executor() {
            System.out.println("产品A的功能");
        }
    }

    public static class ProductB {
        public void executor() {
            System.out.println("产品B的功能");
        }
    }

    public static class ProductC {
        public void executor() {
            System.out.println("产品C的功能");
        }
    }

    public static class ProductAFactory {
        public static ProductA create() {
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品A的特殊逻辑");
            return new ProductA();
        }
    }

    public static class ProductBFactory {
        public static ProductB create() {
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品B的特殊逻辑");
            return new ProductB();
        }
    }

    public static class ProductCFactory {
        public static ProductC create() {
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品C的特殊逻辑");
            return new ProductC();
        }
    }
}
