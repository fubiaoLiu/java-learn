package com.xiaoliu.learn.framework.builder;

/**
 * @description: 优化后的构造器模式 - 目前基本上一些流行开源框架都是使用的这种变种模式
 * @author: liufb
 * @create: 2020/5/17 15:28
 **/
public class OptimizedBuilderPattern {
    public static void main(String[] args) {
        Product product = new ConcreteBuilder()
                .setName("产品1号")
                .setModel("1_pro")
                .setPrice(99.99)
                .create();
        System.out.println(product);
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

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", model='" + model + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public interface Builder {
        Builder setName(String name);

        Builder setModel(String model);

        Builder setPrice(Double price);

        Product create();
    }

    public static class ConcreteBuilder implements Builder {
        private Product product = new Product();

        @Override
        public Builder setName(String name) {
            System.out.println("校验name是否合法");
            product.setName(name);
            return this;
        }

        @Override
        public Builder setModel(String model) {
            System.out.println("产品型号名称转换为型号代码");
            product.setModel(model);
            return this;
        }

        @Override
        public Builder setPrice(Double price) {
            System.out.println("产品价格类型转换");
            product.setPrice(price);
            return this;
        }

        @Override
        public Product create() {
            return product;
        }
    }
}
