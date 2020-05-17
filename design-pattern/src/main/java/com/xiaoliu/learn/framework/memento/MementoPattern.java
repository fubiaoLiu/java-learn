package com.xiaoliu.learn.framework.memento;

import javax.swing.*;

/**
 * @description: 使用备忘录模式
 * 场景：如果系统会生产出来一份中间数据，这个中间数据要么无法一直在内存保存，要么可能会被修改，
 * 所以必须要把这份数据暂存一份。
 * @author: liufb
 * @create: 2020/5/17 22:52
 **/
public class MementoPattern {
    public static void main(String[] args) {
        Originator originator = new Originator();

        // 1、准备中间数据
        originator.prepare();
        // 2、将中间数据保存到备忘录
        Memento memento = originator.createMemento();
        // 3、将备忘录保存到备忘录管理器中去
        Caretaker caretaker = new Caretaker();
        caretaker.saveMemento(memento);
        // 4、基于中间数据执行A方法
        originator.executorA();
        // 5、从备忘录管理器中获取备忘录
        memento = caretaker.retrieveMemento();
        // 6、将备忘录中的数据设置回原发器掌趣
        originator.setMemento(memento);
        // 7、接着执行B方法
        originator.executorB();
    }

    public interface Memento {
    }

    public static class Originator {
        private String state;

        public void prepare() {
            state = "中间数据";
        }

        public void executorA() {
            System.out.println("基于中间数据[" + state + "]执行A方法的逻辑");
            state += ",A方法的结果";
        }

        public void executorB() {
            System.out.println("基于中间数据[" + state + "]执行B方法的逻辑");
            state += ",B方法的结果";
        }

        public Memento createMemento() {
            return new MementoImpl(state);
        }

        public void setMemento(Memento memento) {
            MementoImpl mementoImpl = (MementoImpl) memento;
            this.state = mementoImpl.getState();
        }

        private static class MementoImpl implements Memento {
            private String state;

            public MementoImpl(String state) {
                this.state = state;
            }

            public String getState() {
                return state;
            }
        }
    }

    public static class Caretaker {
        private Memento memento;

        public void saveMemento(Memento memento) {
            this.memento = memento;
        }

        public Memento retrieveMemento() {
            return memento;
        }
    }
}
