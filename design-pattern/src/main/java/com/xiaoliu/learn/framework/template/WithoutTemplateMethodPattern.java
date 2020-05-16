package com.xiaoliu.learn.framework.template;

/**
 * @description: 不用模板方法
 * 有A、B、C三个功能，三个功能有一部分通用的逻辑和一部分各自功能特有的逻辑。
 * 比如商品优惠活动：有积分兑换优惠、优惠券优惠、在线支付优惠三个活动，
 * 三个活动下单都要先计算订单的总金额等等，然后再通过各自活动的规则进行优惠、减少订单实付金额。
 *
 * 不用模板方法的缺点：现在有三个活动都有计算订单金额的相同代码，
 * 如果订单金额计算的逻辑改了，需要在三个地方都改一遍，如果漏了一个没改，就会有bug。
 * @author: liufb
 * @create: 2020/5/16 12:11
 **/
public class WithoutTemplateMethodPattern {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        a.method();
        b.method();
        c.method();
    }

    public static class A {
        public void method() {
            System.out.println("计算订单总金额");
            System.out.println("A活动优惠扣减");
        }
    }

    public static class B {
        public void method() {
            System.out.println("计算订单总金额");
            System.out.println("B活动优惠扣减");
        }
    }

    public static class C {
        public void method() {
            System.out.println("计算订单总金额");
            System.out.println("C活动优惠扣减");
        }
    }
}
