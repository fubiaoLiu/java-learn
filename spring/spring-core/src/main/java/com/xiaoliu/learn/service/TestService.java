package com.xiaoliu.learn.service;

import com.xiaoliu.learn.dao.TestDao;
import org.springframework.stereotype.Service;

/**
 * @description: -
 * 注解(@Service)类TestService在通过构造方法注入TestDao是可以注入的，
 * 但这并不算自动注入。(以前总是在构造方法上加@Autowired注解，其实不需要)
 * <p>
 * Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName);
 * if (ctors != null || mbd.getResolvedAutowireMode() == AUTOWIRE_CONSTRUCTOR ||
 * mbd.hasConstructorArgumentValues() || !ObjectUtils.isEmpty(args)) {
 * return autowireConstructor(beanName, mbd, ctors, args);
 * }
 * <p>
 * Spring在创建Bean的时候有这样一段代码，先推断是否有构造方法，再判断自动注入模型是不是AUTOWIRE_CONSTRUCTOR。
 * 这里我提供了一个带参的构造方法，第一个条件成立，调用autowireConstructor完成自动注入，从这里来看貌似是自动注入的。
 * 但是第二个条件mbd.getResolvedAutowireMode()等于0，自动注入模型为AUTOWIRE_NO，从这里来看并不属于自动注入。
 * (这里我们没有指定类的自动注入模型，同样说明了注解类默认的自动注入模型为NO)
 * <p>
 * @Autowired 注入属性的方式是利用了java的反射知识，field.set(value,targetObject)，是setter注入方式的一种变体;
 * 另外，@Autowired 不属于自动装配，@Autowired 和 byType 本质上没有关系。
 * 1、Spring官网上面可以看到Spring只提出了4中自动装配模型(no、byName、byType、constructor)，并没有@Autowired。
 * 2、在不配置BeanFactoryPostProcessor和修改beanDefinition的情况下注解的类是不支持自动装配的。
 * 3、对A类的某个属性上面加上@Autowired之后，没有改变A类的autowireMode属性值，默认还是NO。
 * 4、在Spring完成属性赋值的代码org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean
 * 方法中有这么一段代码，在使用@Autowired注解去注入一个属性时，会跳过这个判断，不属于自动注入。
 * <p>
 * int resolvedAutowireMode = mbd.getResolvedAutowireMode();
 * if (resolvedAutowireMode == AUTOWIRE_BY_NAME || resolvedAutowireMode == AUTOWIRE_BY_TYPE) {
 * MutablePropertyValues newPvs = new MutablePropertyValues(pvs);
 * if (resolvedAutowireMode == AUTOWIRE_BY_NAME) {
 * autowireByName(beanName, mbd, bw, newPvs);
 * }
 * if (resolvedAutowireMode == AUTOWIRE_BY_TYPE) {
 * autowireByType(beanName, mbd, bw, newPvs);
 * }
 * pvs = newPvs;}
 * @author: FuBiaoLiu
 * @date: 2019/10/28
 */
@Service
//@Scope("prototype")
public class TestService {
    private TestDao dao;

    public TestService(TestDao dao) {
        this.dao = dao;
    }

    public void test1() {
        System.out.println("TestService#test1");
        dao.test();
    }

    public void test2(String str) {
        System.out.println("TestService#test2(" + str + ")");
    }
}
