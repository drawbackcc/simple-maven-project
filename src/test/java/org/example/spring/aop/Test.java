package org.example.spring.aop;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/10 1:40
 */
//不使用springjunitest
public class Test {
    private static ApplicationContext context = null;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("classpath:spring/aop/applicationContext.xml");
    }

    @org.junit.Test
    public void test(){
        PersonService service = (PersonService)context.getBean("personServiceForSpringAop");
        service.deletePerson();
        service.savePerson();
        System.out.println(service.getClass());

    }
}
