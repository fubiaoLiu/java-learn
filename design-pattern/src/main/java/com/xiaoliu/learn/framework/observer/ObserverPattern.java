package com.xiaoliu.learn.framework.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @description: 使用观察者模式
 * @author: liufb
 * @create: 2020/5/17 18:51
 **/
public class ObserverPattern {
    public static void main(String[] args) {
        Subject target = new Subject(1);
        target.addObserver(new ConcreteObserver());
        target.setState(2);
    }

    /**
     * 被观察者
     */
    public static class Subject extends Observable {
        private Integer state;

        public Subject(Integer state) {
            this.state = state;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            // 状态改变
            this.state = state;
            // 通知关联的观察者
            this.setChanged();
            // 推：直接告诉观察者变更的内容
//            this.notifyObservers(state);
            // 拉：告诉观察者内容变更，然后观察者自己在来拉取变更内容
            this.notifyObservers();
        }
    }

    /**
     * 观察者
     */
    public static class ConcreteObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            // 推：目标对象主动推送变更内容
//            Integer state = (Integer) arg;
            // 拉：得知变更后去拉去变更内容
            Subject target = (Subject) o;
            Integer state = target.getState();
            System.out.println("目标对象的状态变化为：" + state);
        }
    }
}
