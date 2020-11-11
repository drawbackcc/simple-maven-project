package org.example.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/aop/applicationContext.xml"})
public class AnyClassTest {

    @Autowired AnyClass proxy;

    @Test
    public void hello() {
        System.out.println(proxy.getClass());
        System.out.println(proxy.getClass().getSuperclass());
        proxy.hello();
        /**
         * class org.example.spring.aop.AnyClass$$EnhancerBySpringCGLIB$$8ae99a77
         * class org.example.spring.aop.AnyClass
         * 开启事务
         * hello
         * 提交事务
         */

        /**
         * AnyClass没有实现接口，使用cglib代理
         */
    }

    @Test
    public void anyMethod(){
        proxy.toString();
    }
}