package com.xiaoliu.learn.addr.annotation;

import com.xiaoliu.learn.addr.config.AddrAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AddrAutoConfiguration.class)
public @interface EnableAddrService {
}
