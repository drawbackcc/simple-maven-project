package org.example.proxy;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 1:56
 */
public class PersonServiceImpl implements PersonService{
    @Override
    public void savePerson() {
        System.out.println("添加");
    }

    @Override
    public void updatePerson() {
        System.out.println("修改");
    }

    @Override
    public void deletePerson() {
        System.out.println("删除");
    }

}
