package com.xiaoliu.learn.framework.factory;

/**
 * @description: 不用抽象工厂模式
 * 假如现在有A、B两个产品，每个产品有3个型号，会根据需求进行组合，比如A1+B1、A2+B2。
 * 如果需求变更，A1+B1组合需要更改为A1+B3，那么在用到A1+B1的所有地方都要修改。
 * @author: liufb
 * @create: 2020/5/16 14:35
 **/
public class WithoutAbstractFactoryPattern {
    public static void main(String[] args) {
        // A1 + B1 -> A1 + B3
        ProductA productA1 = new ProductA1();
        // ProductB productB1 = new ProductB1();
        ProductB productB3 = new ProductB3();
        productA1.executor();
        // productB1.executor();
        productB3.executor();

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
}
