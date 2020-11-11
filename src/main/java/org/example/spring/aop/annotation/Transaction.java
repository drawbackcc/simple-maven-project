package org.example.spring.aop.annotation;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 1:57
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
//切面类
/**
 *  *  @Aspect + @Pointcut()这两个注解就相当于之前配置文件里下边的内容
 *  *  <aop:pointcut id="pointcut" expression="execution(* org.example.spring.aop.*.*(..))" id="perform"/>
 *  *
 *  *  @Before()这个注解就相当于之前配置文件里
 *  *  <aop:aspect ref="myTransaction">
 *  *		<aop:before method="beginTransaction" pointcut-ref="pointcut"/>
 *  *  </aop:aspect>
 */
@Component
@Aspect
//@Component("transactionForSpringAopAnnotation")
public class Transaction {

    //方法一，先定义切点，再增强
    //这个 aaa() 方法没有其他作用，仅仅是用它来标明一下切入点表达式
    @Pointcut("execution(* org.example.spring.aop.annotation.PersonServiceImpl.*(..))")
    public void aaa(){
        System.out.println("这个 aaa() 方法没有其他作用，仅仅是用它来标明一下切入点表达式");
    }

    @Before("aaa()")
    public void beginTransaction(){
        System.out.println("开启事务 ");
    }

    @After("aaa()")
    public void commit(){
        System.out.println("提交事务");
    }

    //方法二，直接注解在增强方法上
    @Around("execution(* org.example.spring.aop.annotation.PersonServiceImpl.*(..))")
    public void around(ProceedingJoinPoint point) throws Throwable{
        System.out.println("环绕通知前……");
        Object result = point.proceed();
        System.out.println("环绕通知后……" + result);
    }

    @Before("execution(* org.example.spring.aop.annotation.PersonServiceImpl.*(..))")
    public void before(){
        System.out.println("执行方法前……1");
    }

    @Before("execution(* org.example.spring.aop.annotation.PersonServiceImpl.*(..))")
    public void before2(){
        System.out.println("执行方法前……2");
    }
    //他妈的同一个方法还能写不同的切面，怎么保证顺序？
}
