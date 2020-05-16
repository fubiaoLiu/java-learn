package com.xiaoliu.learn.framework.facade;

/**
 * @description: 使用外观模式
 * 假设现有2个子系统
 * 子系统A有多个模块1、2、3，子系统B要依赖这三个模块实现一个功能。
 * Service就是一个经典的外观模式，Service会封装该系统的多个DAO组件，对外提供统一的接口。
 * 优点：
 * 系统B不需要care太多的模块，只要调用一个方法实现功能就行。
 * 如果好多地方都调用了这段逻辑，一但逻辑变了，只要修改门面类就行。
 * @author: liufb
 * @create: 2020/5/16 21:30
 **/
public class FacadePattern {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.executor();
    }

    public static class Module1 {
        public void executor() {
            System.out.println("A系统模块1");
        }
    }

    public static class Module2 {
        public void executor() {
            System.out.println("A系统模块2");
        }
    }

    public static class Module3 {
        public void executor() {
            System.out.println("A系统模块3");
        }
    }

    public static class Facade {
        public void executor() {
            Module1 module1 = new Module1();
            Module2 module2 = new Module2();
            Module3 module3 = new Module3();
            module1.executor();
            module2.executor();
            module3.executor();
        }
    }
}
