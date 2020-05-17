package com.xiaoliu.learn.framework.builder;

/**
 * @description: 不用构造器模式
 * 缺点：对于包含大量字段对象的构建，构建代码会非常复杂，
 * 并且构建时可能包含一系列的类型转换、数据校验、数据关联，代码可读性、可维护性会非常差。
 * @author: liufb
 * @create: 2020/5/17 15:28
 **/
public class WithoutBuilderPattern {
    public static void main(String[] args) {
        Product product = new Product();
        System.out.println("校验name是否合法");
        product.setName("产品1号");
        System.out.println("产品型号名称转换为型号代码");
        product.setModel("1_pro");
        System.out.println("产品价格类型转换");
        product.setPrice(99.99);
    }

    public static class Product {
        private String name;
        private String model;
        private Double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
