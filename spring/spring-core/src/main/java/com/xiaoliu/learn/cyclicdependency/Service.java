package com.xiaoliu.learn.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: Service: 依赖于BeanA、BeanB，会根据代码前后顺序依次加载对应的Bean
 * 1、BeanA在前，BeanB在后，会出现循环依赖问题
 * ① 当Spring在试图加载Service时，先构造了Service，然后发现他依赖BeanA，于是就试图去加载BeanA；
 * ② Spring想构造BeanA，但是发现BeanA在Config内部，于是又试图加载Config（此时BeanA仍未构造）；
 * ③ Spring构造了Config的实例，然后发现他依赖BeanB，于是就试图去加载BeanB。
 * ④ Spring构造了BeanB的实例，然后发现他依赖BeanA，于是就试图去加载BeanA。
 * ⑤ Spring发现BeanA还没有实例化，此时Spring发现自己回到了步骤②，一直循环下去...
 * 原因：只有先将配置类的依赖全部满足之后才可以创建他自己声明的其他的Bean。
 * <p>
 * 2、BeanB在前，BeanA在后，不会出现循环依赖问题
 * ① 当Spring在试图加载Service时，先构造了Service，然后发现他依赖BeanB，于是就试图去加载BeanB；
 * ② Spring构造了BeanB的实例，然后发现他依赖BeanA，于是就试图去加载BeanA。
 * ③ Spring发现BeanA在Config内部，于是试图加载Config（此时BeanA仍未构造）；
 * ④ Spring构造了Config的实例，然后发现他依赖BeanB，并且BeanB的实例已经有了，于是将这个依赖填充进Config中。
 * ⑤ Spring发现Config已经完成了构造、填充了依赖，于是想起来构造了BeanA。
 * ⑥ Spring发现BeanA已经有了实例，于是将他给了BeanB，BeanB填充的依赖完成。
 * ⑦ Spring回到了为Service填充依赖的过程，发现还依赖BeanA，于是将BeanA填充给了Service。
 * ⑧ Spring成功完成了初始化操作。
 * <p>
 * 总结：除了构造注入会导致强依赖以外，一个Bean也会强依赖于暴露他的配置类(BeanA强依赖于Config)。
 * 建议：不要对有@Configuration注解的配置类进行Field级的依赖注入。
 * @author: FuBiaoLiu
 * @date: 2019/11/4
 */
public class Service {
    @Autowired
    private BeanA beanA;

    @Autowired
    private BeanB beanB;
}
