package org.example.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:proxy/applicationContext.xml"})
public class PersonServiceProxyTest {
    @Autowired private PersonServiceProxy proxy;

    @Test
    public void savePerson() {
        proxy.savePerson();
    }

    @Test
    public void updatePerson() {
        proxy.updatePerson();
    }

    @Test
    public void deletePerson() {
        proxy.deletePerson();
    }
}