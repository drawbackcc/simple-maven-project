package org.example.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 1:24
 */
public class Proxy implements MethodInterceptor {

    public Object newInstance(Class clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用方法之前========" + object.getClass() + "." + method.getName());
        Object result = methodProxy.invokeSuper(object, args);//写成invoke会进入死循环
        System.out.println("调用方法之后========" + object.getClass() + "." + method.getName());
        return result;
    }

    public static void main(String[] args) {
        Sample sample = (Sample) new Proxy().newInstance(Sample.class);
        System.out.println(sample.greet("aaaaaaaaaaaaaaaaa"));
        sample.finalMethod();
    }
}
