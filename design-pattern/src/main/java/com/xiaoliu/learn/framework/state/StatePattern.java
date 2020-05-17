package com.xiaoliu.learn.framework.state;

/**
 * @description: 使用状态模式 - 适合有状态的数据
 * 数据有状态，状态就一定会流转，不同的状态逻辑放不同的state类，有一个类负责状态之间的流转以及状态逻辑的执行。
 * @author: liufb
 * @create: 2020/5/17 21:45
 **/
public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context(new StateA());
        context.executor(2);
        context.executor(3);
        context.executor(4);
    }

    public interface State {
        void executor();
    }

    public static class StateA implements State {

        @Override
        public void executor() {
            System.out.println("执行A状态的逻辑");
        }
    }

    public static class StateB implements State {

        @Override
        public void executor() {
            System.out.println("执行B状态的逻辑");
        }
    }

    public static class StateC implements State {

        @Override
        public void executor() {
            System.out.println("执行C状态的逻辑");
        }
    }

    public static class StateD implements State {

        @Override
        public void executor() {
            System.out.println("执行D状态的逻辑");
        }
    }

    public static class Context {
        private State state;

        public Context(State state) {
            this.state = state;
        }

        public void executor(int stateType) {
            if (stateType == 1) {
                this.state = new StateA();
            } else if (stateType == 2) {
                this.state = new StateB();
            } else if (stateType == 3) {
                this.state = new StateC();
            } else {
                this.state = new StateD();
            }
            this.state.executor();
        }
    }
}
