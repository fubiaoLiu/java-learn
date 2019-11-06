package com.xiaoliu.learn.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @description: 定义切面
 * @概念: <p/>
 * aspect(切面): 一定要给spring去管理  抽象  aspectj->类  xml->label
 * pointcut(切点): 表示连接点的集合
 * （我的理解: PointCut是JoinPoint的谓语，这是一个动作，主要是告诉通知连接点在哪里，切点表达式决定 JoinPoint 的数量）
 * Joinpoint(连接点): 目标对象中的方法
 * （我的理解: JoinPoint是要关注和增强的方法，也就是我们要作用的点）
 * Weaving: 把代理逻辑加入到目标对象上的过程叫做织入
 * target: 目标对象、原始对象
 * this: JDK代理时，指向接口和代理类Proxy，CGLIB代理时，指向接口和子类(不使用proxy)。
 * aop Proxy(代理对象): 包含了原始对象的代码和增加后的代码的那个对象
 * advice(通知): 位置 + 逻辑
 * @通知执行顺序: Around Start -> Before -> Around End -> After
 * @author: FuBiaoLiu
 * @date: 2019/10/14
 */
@Component
@Aspect
//@Scope("prototype") // 设置切面为原型，会为目标对象为原型的类创建单独的切面
//@Aspect("perthis(this(com.xiaoliu.learn.dao.TestDao))")
public class MyAspect {

    /**
     * com.xiaoliu.learn.service包或子包下的所有类的方法
     *
     * @execution: 用于匹配方法执行join points连接点，最小粒度为方法，在aop中主要使用。
     */
    @Pointcut("execution(* com.xiaoliu.learn.service.*.*(..))")
    private void pointCutExecution() {
    }

    /**
     * com.xiaoliu.learn.service包下所有的类
     *
     * @within: 最小粒度为类
     */
    @Pointcut("within(com.xiaoliu.learn.service.*)")
    private void pointCutWithin() {
    }

    /**
     * 参数为String的方法
     */
    @Pointcut("args(java.lang.String)")
    private void pointCutArgs() {
    }

    /**
     * 目标对象为TestDao
     */
    @Pointcut("this(com.xiaoliu.learn.dao.TestDao)")
    private void pointCutTarget() {
    }

    /**
     * 代理对象为TestDao
     */
    @Pointcut("this(com.xiaoliu.learn.dao.TestDao)")
    private void pointCutThis() {
    }

    /**
     * 前置通知: 切点定义引用pointCutExecution()方法的表达式
     */
    @Before("pointCutExecution()")
    private void before() {
        System.out.println("------ Aspect before... ------");
    }

    /**
     * 后置通知: 切点定义直接使用表达式
     */
    @After("execution(* com.xiaoliu.learn.service.*.*(..))")
    private void after() {
        System.out.println("------ Aspect after... ------");
    }

    /**
     * 后置通知: args(String,..)表示方法第一个参数是String类型
     */
    @After("execution(* com.xiaoliu.learn.service.*.*(..)) && args(String,..)")
    private void after1() {
        System.out.println("------ Aspect after1... ------");
    }

    /**
     * 环绕通知: 切点定义引用pointCutExecution() 和 pointCutWithin() 的表达式('&&'同Java语法中的'&&')
     * <p>
     * ProceedingJoinPoint 继承了JoinPoint，proceed()这个是aop代理链执行的方法，并扩充实现了proceed()方法，用于继续执行连接点。
     * JoinPoint仅能获取相关参数，无法执行连接点。
     */
    @Around("pointCutExecution() && pointCutWithin()")
    private void around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("------ Aspect around head... ------");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("------ Aspect around tail... ------");
    }

    @Before("pointCutTarget()")
    private void daoBefore(){
        System.out.println("------ Aspect daoBefore... ------");
    }
}
