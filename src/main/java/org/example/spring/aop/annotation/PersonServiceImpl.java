package org.example.spring.aop.annotation;

import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 1:56
 */
//目标类
//@Repository("personServiceForSpringAopAnnotation")
@Repository
public class PersonServiceImpl implements PersonService{
    @Override
    public String savePerson() {
        System.out.println("添加");
        return "保存成功！";
    }

    @Override
    public void updatePerson() {
        System.out.println("修改");
    }

    @Override
    public void deletePerson() {
        System.out.println("删除");
//        int i = 1 / 0;//抛出异常,后置通知不再执行
    }

}
