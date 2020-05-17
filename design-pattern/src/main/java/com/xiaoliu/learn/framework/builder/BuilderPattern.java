package com.xiaoliu.learn.framework.builder;

/**
 * @description: 使用构造器模式
 * 优点：
 * 1、通过Builder接口将复杂构建步骤拆分成了多个部分，代码逻辑清晰，可维护性、可扩展性都很好。
 * 2、将对象的构建过程，封装在了director里，构建逻辑修改只改一个地方就行。
 * 3、相对于工厂，有一个很好的抽象设计，director和builder
 * @author: liufb
 * @create: 2020/5/17 15:28
 **/
public class BuilderPattern {
    public static void main(String[] args) {
        Director director = new Director(new ConcreteBuilder());
        Product product = director.builder("产品1号", "1_pro", 99.99);
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
        void setName(String name);

        void setModel(String model);

        void setPrice(Double price);

        Product create();
    }

    public static class ConcreteBuilder implements Builder {
        private Product product = new Product();

        @Override
        public void setName(String name) {
            System.out.println("校验name是否合法");
            product.setName(name);
        }

        @Override
        public void setModel(String model) {
            System.out.println("产品型号名称转换为型号代码");
            product.setModel(model);
        }

        @Override
        public void setPrice(Double price) {
            System.out.println("产品价格类型转换");
            product.setPrice(price);
        }

        @Override
        public Product create() {
            return product;
        }
    }

    /**
     * 构建逻辑和构建步骤分别封装在Builder和Director中，如果有改变两者相互不影响。
     */
    public static class Director {
        private Builder builder;

        public Director(Builder builder) {
            this.builder = builder;
        }

        public Product builder(String name, String model, Double price) {
            builder.setName(name);
            builder.setModel(model);
            builder.setPrice(price);
            return builder.create();
        }
    }
}
