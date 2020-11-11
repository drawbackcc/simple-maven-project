package org.example.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 0:50
 */
public class SpeakableProxyHandler implements InvocationHandler {
    private Object target;

    public SpeakableProxyHandler(){}
    public SpeakableProxyHandler(Object target){
        this.target = target;
    }

    public Object create(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(target.getClass());
        System.out.println(proxy.getClass());
        System.out.println(method.getDeclaringClass());
        System.out.println("调用" + target.getClass() + "的方法" + method.getName() + "之前");
        Object result = method.invoke(target, args);
        System.out.println("调用" + proxy.getClass() + "的方法" + method.getName() + "之后");
        return result;
    }

    public static void main(String[] args) {
        Speakable proxy1 = (Speakable) Proxy.newProxyInstance(Speakable.class.getClassLoader(),
                new Class[]{Speakable.class},
                new SpeakableProxyHandler(new Student()));
        System.out.println(proxy1.getClass());
        System.out.println(proxy1.say("hello"));
        System.out.println("==================================");

        Student student2 = new Student();
        Speakable proxy2 = (Speakable) Proxy.newProxyInstance(student2.getClass().getClassLoader(),
                student2.getClass().getInterfaces(),
                new SpeakableProxyHandler(student2));
        System.out.println(proxy2.getClass());
        System.out.println(proxy2.say("hello"));
        System.out.println("==================================");

        SpeakableProxyHandler handler = new SpeakableProxyHandler();
        Speakable proxy3 = (Speakable)handler.create(new Student());
        System.out.println(proxy3.say("hello"));
    }
}
