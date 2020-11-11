package org.example.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/30 22:45
 */
public class Sample {
    public String greet(String message){
        System.out.println(message);
        return "greet";
    }

    public final void finalMethod(){
        System.out.println("a final method");
    }

    public static void main(final String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sample.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                System.out.println("执行" + object.getClass() + "的方法" + method.getName() + "之前," + df.format(new Date()));
                System.out.println("MethodProxy:" + proxy.getClass());

                Object result = proxy.invokeSuper(object, args);
//                Object result2 = method.invoke(object, args);
                //必须注意死循环。因为invoke中调用的任何原代理类方法，均会重新代理到invoke方法中
//                System.out.println(result == result2);

                System.out.println("执行" + object.getClass() + "的方法" + method.getName() + "之后," + df.format(new Date()));
                return result;
            }
        });

        Sample sample = (Sample) enhancer.create();
        System.out.println("代理类：" + sample.getClass());
        sample.greet("hello world");
        System.out.println(sample instanceof Sample);
        System.out.println(sample.getClass().getSuperclass());
        Method[] methods = sample.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        sample.finalMethod();
    }
}
