package com.xiaoliu.learn.framework.template;

/**
 * @description: 使用模板方法
 * 优点：将通用的代码抽取出来放在父类中，方便维护。不同的代码子类在各自的代码中实现。
 * @author: liufb
 * @create: 2020/5/16 12:11
 **/
public class TemplateMethodPattern {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        a.calculate();
        b.calculate();
        c.calculate();
    }

    public interface DiscountCalculator {
        void calculate();
    }

    public static abstract class AbstractDiscountCalculator implements DiscountCalculator {
        @Override
        public void calculate() {
            commonCalculator();
            specificCalculator();
        }

        private void commonCalculator() {
            System.out.println("计算订单总金额");
        }

        public abstract void specificCalculator();
    }

    public static class A extends AbstractDiscountCalculator {
        @Override
        public void specificCalculator() {
            System.out.println("A活动优惠扣减");
        }
    }

    public static class B extends AbstractDiscountCalculator {
        @Override
        public void specificCalculator() {
            System.out.println("B活动优惠扣减");
        }
    }

    public static class C extends AbstractDiscountCalculator {
        @Override
        public void specificCalculator() {
            System.out.println("C活动优惠扣减");
        }
    }
}
