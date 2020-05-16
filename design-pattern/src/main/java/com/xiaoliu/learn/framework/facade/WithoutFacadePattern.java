package com.xiaoliu.learn.framework.facade;

/**
 * @description: 不用外观模式
 * 假设现有2个子系统
 * 子系统A有多个模块1、2、3，子系统B要依赖这三个模块实现一个功能
 * 缺点：
 * 系统B需要了解系统A中的多个模块，模块越多维护成本越高。
 * 系统A的这些组合模块如果被多个地方用到了或者系统B中有多个地方用到了，一旦业务逻辑修改了，可能需要修改多处代码。
 * @author: liufb
 * @create: 2020/5/16 21:30
 **/
public class WithoutFacadePattern {
    public static void main(String[] args) {
        Module1 module1 = new Module1();
        Module2 module2 = new Module2();
        Module3 module3 = new Module3();
        module1.executor();
        module2.executor();
        module3.executor();
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
}
