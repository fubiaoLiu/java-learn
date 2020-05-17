package com.xiaoliu.learn.framework.strategy;

/**
 * @description: 使用策略模式
 * 场景：假设现有一个订单计价的功能，有多种计价方式。
 * 要点：
 * 1、将if else的代码封装到不同的策略类中去
 * 2、将选择哪种策略的逻辑放到工厂类中去，选择策略的代码务必简洁
 * 3、context可有可无，如果策略执行的逻辑较为复杂，可以使用context封装策略类的执行逻辑
 * @author: liufb
 * @create: 2020/5/17 21:13
 **/
public class StrategyPattern {
    public static void main(String[] args) {
        int discountType = 1;
        DiscountCalculateStrategy strategy = DiscountCalculateStrategyFactory.getDiscountCalculateStrategy(discountType);
        Context context = new Context(strategy);
        context.calculate();
    }

    public interface DiscountCalculateStrategy {
        void calculate();
    }

    public static class DiscountCalculateStrategyA implements DiscountCalculateStrategy {
        @Override
        public void calculate() {
            System.out.println("执行A计价方式的计价逻辑");
        }
    }

    public static class DiscountCalculateStrategyB implements DiscountCalculateStrategy {
        @Override
        public void calculate() {
            System.out.println("执行B计价方式的计价逻辑");
        }
    }

    public static class DiscountCalculateStrategyC implements DiscountCalculateStrategy {
        @Override
        public void calculate() {
            System.out.println("执行C计价方式的计价逻辑");
        }
    }

    public static class DiscountCalculateStrategyD implements DiscountCalculateStrategy {
        @Override
        public void calculate() {
            System.out.println("执行D计价方式的计价逻辑");
        }
    }

    public static class DiscountCalculateDefaultStrategy implements DiscountCalculateStrategy {
        @Override
        public void calculate() {
            System.out.println("执行默认计价方式的计价逻辑");
        }
    }

    public static class DiscountCalculateStrategyFactory {
        public static DiscountCalculateStrategy getDiscountCalculateStrategy(int discountType) {
            if (discountType == 1) {
                return new DiscountCalculateStrategyA();
            } else if (discountType == 2) {
                return new DiscountCalculateStrategyB();
            } else if (discountType == 3) {
                return new DiscountCalculateStrategyC();
            } else if (discountType == 4) {
                return new DiscountCalculateStrategyD();
            } else {
                return new DiscountCalculateDefaultStrategy();
            }
        }
    }

    public static class Context {
        private DiscountCalculateStrategy discountCalculateStrategy;

        public Context(DiscountCalculateStrategy discountCalculateStrategy) {
            this.discountCalculateStrategy = discountCalculateStrategy;
        }

        public void calculate() {
            discountCalculateStrategy.calculate();
        }
    }
}
