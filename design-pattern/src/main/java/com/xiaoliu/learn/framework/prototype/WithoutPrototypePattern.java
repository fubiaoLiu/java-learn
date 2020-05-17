package com.xiaoliu.learn.framework.prototype;

/**
 * @description: 不用原型模式
 * 有一个对象，需要进行拷贝，如果不用原型模式，那么就需要手动拷贝。
 * 缺点：
 * 1、代码的拷贝逻辑，需要在调用方实现
 * 2、相同的拷贝逻辑可能会在多个地方出现，如果需要修改，要修改多处代码，可维护性、可扩展性差。
 * @author: liufb
 * @create: 2020/5/17 18:10
 **/
public class WithoutPrototypePattern {
    public static void main(String[] args) {
        Product product = new Product("产品A", new Component("组件1"));

        Product copyProduct = new Product(product.getName(), product.getComponent());
        System.out.println(copyProduct);
    }

    public static class Product {
        private String name;
        private Component component;

        public Product(String name, Component component) {
            this.name = name;
            this.component = component;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Component getComponent() {
            return component;
        }

        public void setComponent(Component component) {
            this.component = component;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", component=" + component +
                    '}';
        }
    }

    public static class Component {
        private String name;

        public Component(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
