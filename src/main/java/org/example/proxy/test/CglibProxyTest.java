package org.example.proxy.test;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/4 22:59
 */
public class CglibProxyTest implements MethodInterceptor {
//    private Object target;
//
//    public CglibProxyTest(Object target){
//        this.target = target;
//    }
    private CglibProxyTest(){}

    public static <T extends Target> Target getProxyInstance(Class<T> targetClass){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new CglibProxyTest());
        return (Target)enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println();
        return methodProxy.invokeSuper(object, args);
    }

    public static void main(String[] args) {
        Target cglibProxy = CglibProxyTest.getProxyInstance(TargetImpl.class);
        System.out.println(cglibProxy.getClass());
        System.out.println(cglibProxy.getClass().getSuperclass());
        System.out.println(cglibProxy.getClass().getSuperclass().getInterfaces());
        System.out.println(cglibProxy.test(242));
    }
}
