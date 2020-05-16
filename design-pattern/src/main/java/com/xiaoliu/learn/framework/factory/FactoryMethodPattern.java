package com.xiaoliu.learn.framework.factory;

/**
 * @description: 使用工厂方法模式
 * 多个工厂，生成不同产品时有一些通用的逻辑
 * @author: liufb
 * @create: 2020/5/16 14:35
 **/
public class FactoryMethodPattern {
    public static void main(String[] args) {
        Product productA = ProductAFactory.getInstance().create();
        Product productB = ProductBFactory.getInstance().create();
        Product productC = ProductCFactory.getInstance().create();
        productA.executor();
        productB.executor();
        productC.executor();
    }

    public interface Product {
        void executor();
    }

    public static class ProductA implements Product {
        @Override
        public void executor() {
            System.out.println("产品A的功能");
        }
    }

    public static class ProductB implements Product {
        @Override
        public void executor() {
            System.out.println("产品B的功能");
        }
    }

    public static class ProductC implements Product {
        @Override
        public void executor() {
            System.out.println("产品C的功能");
        }
    }

    public interface Factory {
        Product create();
    }

    public static abstract class AbstractFactory implements Factory {
        @Override
        public Product create() {
            common();
            return specific();
        }

        private void common() {
            System.out.println("生产产品的通用逻辑");
        }

        protected abstract Product specific();
    }

    public static class ProductAFactory extends AbstractFactory {
        private static ProductAFactory instance = new ProductAFactory();

        public static ProductAFactory getInstance() {
            return instance;
        }

        @Override
        protected Product specific() {
            System.out.println("生产产品A的特殊逻辑");
            return new ProductA();
        }
    }

    public static class ProductBFactory extends AbstractFactory {
        private static ProductBFactory instance = new ProductBFactory();

        public static ProductBFactory getInstance() {
            return instance;
        }

        @Override
        protected Product specific() {
            System.out.println("生产产品B的特殊逻辑");
            return new ProductB();
        }
    }

    public static class ProductCFactory extends AbstractFactory {
        private static ProductCFactory instance = new ProductCFactory();

        public static ProductCFactory getInstance() {
            return instance;
        }

        @Override
        protected Product specific() {
            System.out.println("生产产品C的特殊逻辑");
            return new ProductC();
        }
    }
}
