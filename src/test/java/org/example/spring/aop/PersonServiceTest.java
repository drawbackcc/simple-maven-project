package org.example.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/aop/applicationContext.xml"})
public class PersonServiceTest {

    @Autowired PersonService proxy;//这里注入PersonServiceImpl会出错


    @Test
    public void savePerson() {
        System.out.println(proxy.getClass());
        System.out.println(proxy.getClass().getSuperclass());
        System.out.println(proxy.getClass().getInterfaces());
        proxy.savePerson();
        /**
         * class com.sun.proxy.$Proxy13
         * class java.lang.reflect.Proxy
         * [Ljava.lang.Class;@1eb5174b
         * 开启事务
         * 添加
         * 提交事务
         */

        /**
         *实现类是PersonServiceImpl，由于实现了接口，所以采用jdk动态代理
         */
    }

    @Test
    public void updatePerson() {
        proxy.updatePerson();
    }

    @Test
    public void deletePerson() {
        proxy.deletePerson();
    }

    @Test
    public void anyMethod(){
        proxy.toString();
        proxy.hashCode();
    }

}