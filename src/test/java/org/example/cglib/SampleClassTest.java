package org.example.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.InvocationHandler;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;


public class SampleClassTest {

    @Test
    public void greet() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sample.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "哈哈哈";
            }
        });

        Sample sample = (Sample)enhancer.create();
        System.out.println("result:" + sample.greet("你好"));//拦截greet方法，输出哈哈哈
        System.out.println("result:" + sample.toString());
    }

    @Test
    public void testFixedValue(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sample.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "吼吼吼";
            }
        });
        Sample proxy = (Sample) enhancer.create();
        System.out.println(proxy.greet("嘻嘻嘻"));
        System.out.println(proxy.toString());
//        System.out.println(proxy.getClass());
//        System.out.println(proxy.hashCode());
    }

    @Test
    public void testInvocationHandler() throws Exception{
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sample.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(proxy.getClass());
                System.out.println(method.getDeclaringClass());
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
//                    method.invoke(proxy, args);死循环
                    return "hello cglib";
                }else{
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });
        Sample proxy = (Sample) enhancer.create();
        System.out.println(proxy.greet("嘻嘻嘻"));
        Assert.assertEquals("hello cglib", proxy.greet("嘻嘻嘻"));
        Assert.assertNotEquals("Hello cglib", proxy.toString());
    }

    @Test
    public void finalMethod() {
    }
}