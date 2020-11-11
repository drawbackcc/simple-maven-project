package org.example.proxyjdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 2:16
 */
public class PersonServiceInvocationHandler implements InvocationHandler {

    private Object target;
    private Transaction transaction;

    public PersonServiceInvocationHandler(Object target, Transaction transaction){
        this.target = target;
        this.transaction = transaction;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        transaction.beginTransaction();
        Object result = method.invoke(target, args);
        transaction.commit();
        return result;
    }

    public static void main(String[] args) {
        PersonService proxy =  (PersonService) new PersonServiceInvocationHandler(new PersonServiceImpl(), new Transaction()).getProxyInstance();
        proxy.savePerson();
        proxy.deletePerson();
        proxy.updatePerson();
    }
}
