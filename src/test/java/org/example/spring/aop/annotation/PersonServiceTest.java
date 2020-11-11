package org.example.spring.aop.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/aop/annotation/applicationContext.xml"})
public class PersonServiceTest {

    @Autowired PersonService personService;

    @Test
    public void savePerson() {
        System.out.println(personService.getClass());
        System.out.println(personService.getClass().getSuperclass());
        personService.savePerson();
    }

    @Test
    public void updatePerson() {
        personService.updatePerson();
    }

    @Test
    public void deletePerson() {
        personService.deletePerson();
    }
}