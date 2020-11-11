package org.example.proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/4 22:58
 */
public class JdkDynamicProxyTest implements InvocationHandler {
    private Target target;
    private JdkDynamicProxyTest(Target target){
        this.target = target;
    }

    public static Target getProxyInstance(Target target){
//        System.out.println(target.getClass());
//        System.out.println(target.getClass().getInterfaces());
//        return (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(),
//                new Class[]{target.getClass()},
//                new JdkDynamicProxyTest(target));
        return (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new JdkDynamicProxyTest(target));

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    public static void main(String[] args) {
        Target targetImp = new TargetImpl();
        Target dynamicProxy = JdkDynamicProxyTest.getProxyInstance(targetImp);
        System.out.println(dynamicProxy.getClass());
        System.out.println(dynamicProxy.getClass().getInterfaces());
        System.out.println(targetImp.getClass());
        System.out.println(targetImp.getClass().getInterfaces());
        System.out.println(dynamicProxy.test(212));
    }
}
