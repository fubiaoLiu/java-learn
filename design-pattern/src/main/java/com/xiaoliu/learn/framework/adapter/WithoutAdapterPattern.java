package com.xiaoliu.learn.framework.adapter;

/**
 * @description: 不用适配器模式
 * @author: liufb
 * @create: 2020/5/16 11:54
 **/
public class WithoutAdapterPattern {
    public static void main(String[] args) {
        // 在调用方的代码中，融合了两个版本的接口，如果两个版本的接口的规范、风格都完全不同，
        // 调用方理解和维护的成功就很高。甚至公司强制要求接口按照新的规范来，老版本接口的实现类就不能用了，怎么办？
        InterfaceV1 interfaceV1 = new InterfaceV1Impl();
        InterfaceV2 interfaceV2 = new InterfaceV2Impl();
        interfaceV1.oldExecutor();
        interfaceV2.newExecutor();
    }

    public interface InterfaceV1 {
        void oldExecutor();
    }

    public static class InterfaceV1Impl implements InterfaceV1 {
        @Override
        public void oldExecutor() {
            System.out.println("V1功能");
        }
    }

    public interface InterfaceV2 {
        void newExecutor();
    }

    public static class InterfaceV2Impl implements InterfaceV2 {
        @Override
        public void newExecutor() {
            System.out.println("V2功能");
        }
    }
}
