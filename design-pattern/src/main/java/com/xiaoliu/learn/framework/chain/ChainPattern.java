package com.xiaoliu.learn.framework.chain;

/**
 * @description: 使用责任链模式 - 将各个步骤的功能封装到不同的类中，
 * 如果要更改各步骤的执行顺序，只需要改变调用链的顺序即可。
 * 思想：
 * 1、将一个业务流程中的多个步骤拆分开来，每一个步骤封装到一个Handle中去。
 * 2、支持业务流程基于Handler动态组装，不同的业务流程，Handler代表的代码组件可以复用。
 * 好处：
 * 1、如果要对某个步骤的代码逻辑进行调整，修改一个Handler即可。
 * 2、如果业务流程修改，不需要拷贝大量代码，只要基于Handler动态组装业务流程即可。
 * @author: liufb
 * @create: 2020/5/18 9:46
 **/
public class ChainPattern {
    public static void main(String[] args) {
        Module thirdModule = new ModuleC(null);
        Module secondModule = new ModuleB(thirdModule);
        Module firstModule = new ModuleA(secondModule);
        firstModule.executor();
    }

    public interface Module {
        void executor();
    }

    public static abstract class AbstractModule implements Module {
        protected Module nextModule;

        public AbstractModule(Module nextModule) {
            this.nextModule = nextModule;
        }

        @Override
        public abstract void executor();
    }

    public static class ModuleA extends AbstractModule {
        public ModuleA(Module nextModule) {
            super(nextModule);
        }

        @Override
        public void executor() {
            System.out.println("执行模块A的功能");
            if (nextModule != null) {
                nextModule.executor();
            }
        }
    }

    public static class ModuleB extends AbstractModule {
        public ModuleB(Module nextModule) {
            super(nextModule);
        }

        @Override
        public void executor() {
            System.out.println("执行模块B的功能");
            if (nextModule != null) {
                nextModule.executor();
            }
        }
    }

    public static class ModuleC extends AbstractModule {
        public ModuleC(Module nextModule) {
            super(nextModule);
        }

        @Override
        public void executor() {
            System.out.println("执行模块C的功能");
            if (nextModule != null) {
                nextModule.executor();
            }
        }
    }
}
