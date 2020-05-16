package com.xiaoliu.learn.framework.factory;

/**
 * @description: 使用抽象工厂模式
 * 假如现在有A、B两个产品，每个产品有3个型号，会根据需求进行组合，比如A1+B1、A2+B2。
 * 如果需求变更，A1+B1组合需要更改为A1+B3，只需要修改对应的工厂就好了，调用方都不需要改。
 * @author: liufb
 * @create: 2020/5/16 14:35
 **/
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        // A1 + B1 -> A1 + B3
        ProductA productA1 = Factory1.getInstance().createProductA();
        ProductB productB1 = Factory1.getInstance().createProductB();
        productA1.executor();
        productB1.executor();

        System.out.println("----------------");

        // A2 + B2
        ProductA productA2 = new ProductA2();
        ProductB productB2 = new ProductB2();
        productA2.executor();
        productB2.executor();
    }

    public interface ProductA {
        void executor();
    }

    public static class ProductA1 implements ProductA {
        @Override
        public void executor() {
            System.out.println("产品A1的功能");
        }
    }

    public static class ProductA2 implements ProductA {
        @Override
        public void executor() {
            System.out.println("产品A2的功能");
        }
    }

    public static class ProductA3 implements ProductA {
        @Override
        public void executor() {
            System.out.println("产品A3的功能");
        }
    }

    public interface ProductB {
        void executor();
    }

    public static class ProductB1 implements ProductB {
        @Override
        public void executor() {
            System.out.println("产品B1的功能");
        }
    }

    public static class ProductB2 implements ProductB {
        @Override
        public void executor() {
            System.out.println("产品B2的功能");
        }
    }

    public static class ProductB3 implements ProductB {
        @Override
        public void executor() {
            System.out.println("产品B3的功能");
        }
    }

    public interface Factory {
        ProductA createProductA();

        ProductB createProductB();
    }

    public static class Factory1 implements Factory {
        private static Factory instance = new Factory1();

        public static Factory getInstance() {
            return instance;
        }

        @Override
        public ProductA createProductA() {
            return new ProductA1();
        }

        @Override
        public ProductB createProductB() {
//            return new ProductB1();
            return new ProductB3();
        }
    }

    public static class Factory2 implements Factory {
        private static Factory instance = new Factory2();

        public static Factory getInstance() {
            return instance;
        }

        @Override
        public ProductA createProductA() {
            return new ProductA2();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB2();
        }
    }

    public static class Factory3 implements Factory {
        private static Factory instance = new Factory3();

        public static Factory getInstance() {
            return instance;
        }

        @Override
        public ProductA createProductA() {
            return new ProductA3();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB3();
        }
    }
}
