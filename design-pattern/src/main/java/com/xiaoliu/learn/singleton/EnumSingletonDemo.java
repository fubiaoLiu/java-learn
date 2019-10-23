package com.xiaoliu.learn.singleton;

public class EnumSingletonDemo {
    private EnumSingletonDemo() {
    }

    private enum EnumHolder {
        INSTANCE;
        private EnumSingletonDemo instance = null;

        EnumHolder() {
            instance = new EnumSingletonDemo();
        }

        private EnumSingletonDemo getInstance() {
            return instance;
        }
    }

    public static EnumSingletonDemo getInstance() {
        return EnumHolder.INSTANCE.instance;
    }
}
