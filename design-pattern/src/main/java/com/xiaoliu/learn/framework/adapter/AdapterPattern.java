package com.xiaoliu.learn.framework.adapter;

/**
 * @description: 使用适配器模式
 * 当系统有新老两个版本的接口时，老接口的实现类不能直接用了，就开发一个老接口到新接口的适配器。
 * 适配器实现了新接口，方法实现基于老接口实现类的老方法来实现，
 * 对于调用方而言，只要基于适配器开发即可，就可以统一规范，不用care老接口。
 * @author: liufb
 * @create: 2020/5/16 11:54
 **/
public class AdapterPattern {
    public static void main(String[] args) {
        InterfaceV2 interfaceV21 = new InterfaceV2Adapter(new InterfaceV1Impl());
        InterfaceV2 interfaceV22 = new InterfaceV2Impl();
        interfaceV21.newExecutor();
        interfaceV22.newExecutor();
    }

    public static class InterfaceV2Adapter implements InterfaceV2 {
        private InterfaceV1 interfaceV1;

        public InterfaceV2Adapter(InterfaceV1 interfaceV1) {
            this.interfaceV1 = interfaceV1;
        }

        @Override
        public void newExecutor() {
            interfaceV1.oldExecutor();
        }
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
