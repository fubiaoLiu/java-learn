package com.xiaoliu.learn.framework.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 使用享元模式 - 享(享受)元(元数据)
 * 共享同一个数据。
 * @author: liufb
 * @create: 2020/5/17 23:16
 **/
public class FlyweightPattern {
    public static void main(String[] args) {
        ConcreteProduct concreteProduct1 = ProductFactory.getProduct("产品1");
        concreteProduct1.executor();
        ConcreteProduct concreteProduct2 = ProductFactory.getProduct("产品1");
        concreteProduct2.executor();
        System.out.println(concreteProduct1 == concreteProduct2);
    }

    public interface Product {
        void executor();
    }

    public static class ConcreteProduct implements Product {
        private String name;

        public ConcreteProduct(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void executor() {
            System.out.println("执行产品功能");
        }
    }

    public static class ProductFactory {
        private static Map<String, ConcreteProduct> productMap = new HashMap<>();

        public static ConcreteProduct getProduct(String name) {
            return productMap.computeIfAbsent(name, ConcreteProduct::new);
        }
    }
}
