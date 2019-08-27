# Spring学习Demo
## xiaoliu-spring-boot-starter(自定义Starter)

Starter中定义两个Service：`TimeService`和`AddrService`

- TimeService：定义了一个方法`showTime()`用于显示当前时间，通过`META-INF/spring.factories`文件实现自动配置。添加jar包依赖就可以使用。

- AddrService：定义了一个方法`showAddr()`用于显示配置的`ip:port`(默认127.0.0.1:80)，通过注解方式实现。可以通过在启动类添加`@EnableAddrService`注解或直接使用`@Import(AddrAutoConfiguration.class)`注解导入；除此之外，`AddrAutoConfiguration`配置类添加`@Configuration`注解后，启动类中通过`@ComponentScan()`注解添加扫描包`com.xiaoliu.learn.addr.config`也可以实现配置。`ip:port`的配置如下：
```
xiaoliu:
  addr:
    enabled: true # 默认为true，可省略
    ip: 8.8.8.8
    port: 8888
```
```
实际使用时使用mvn clean install命令推送到本地仓库
```
## xiaoliu-spring-boot-starter-test(测试自定义Starter的使用)

用于测试`xiaoliu-spring-boot-starter`中定义的两个Service，项目定义了两个接口分别用于测试调用`TimeService#showTime()`和`AddrService#showAddr()`方法。
