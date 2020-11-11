package org.example.proxycglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 4:48
 */
public class PersonServiceMethodInterceptor implements MethodInterceptor {
    private Transaction transaction;

    public PersonServiceMethodInterceptor(Transaction transaction){
        this.transaction = transaction;
    }

    public Object getProxyInstance(Class targetClass){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        this.transaction.beginTransaction();
        Object result = methodProxy.invokeSuper(object, args);
        this.transaction.commit();
        return result;
    }

    public static void main(String[] args) {
        PersonService proxy = (PersonService)new PersonServiceMethodInterceptor(new Transaction()).getProxyInstance(PersonService.class);
        proxy.savePerson();
        proxy.updatePerson();
        proxy.deletePerson();
    }
}
