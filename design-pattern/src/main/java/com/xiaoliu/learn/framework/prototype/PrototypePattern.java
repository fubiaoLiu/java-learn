package com.xiaoliu.learn.framework.prototype;

/**
 * @description: 使用原型模式 - 就是在要拷贝的类里实现一个clone()方法，自己拷贝自己。
 * 拷贝包括：深拷贝、浅拷贝
 * 优点：很多地方要克隆这个类的对象，不用自己维护克隆的逻辑，自用修改clone()方法就好了。
 * @author: liufb
 * @create: 2020/5/17 18:10
 **/
public class PrototypePattern {
    public static void main(String[] args) {
        Product product = new Product("产品A", new Component("组件1"));
        Product copyProduct = null;
        try {
            copyProduct = (Product) product.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
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

        @Override
        protected Object clone() throws CloneNotSupportedException {
            // 浅拷贝，仅仅对当前所有的变量进行拷贝
//            return new Product(name, component);
            // 深拷贝，递归对自己引用的对象也进行拷贝
            return new Product(name, (Component) component.clone());
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

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Component(name);
        }
    }
}
