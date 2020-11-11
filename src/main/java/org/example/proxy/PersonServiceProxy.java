package org.example.proxy;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 1:57
 */
public class PersonServiceProxy implements PersonService{
    //目标类
    private PersonService personService;

    //增强类
    private Transaction transaction;

    //利用构造函数将目标类和增强类注入
    public PersonServiceProxy(PersonService personService,Transaction transaction){
        this.personService = personService;
        this.transaction = transaction;
    }

    @Override
    public void savePerson() {
        transaction.beginTransaction();
        personService.savePerson();
        transaction.commit();
    }

    @Override
    public void updatePerson() {
        transaction.beginTransaction();
        personService.updatePerson();
        transaction.commit();
    }

    @Override
    public void deletePerson() {
        transaction.beginTransaction();
        personService.deletePerson();
        transaction.commit();
    }

}
