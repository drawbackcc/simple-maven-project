package org.example.serialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/5 17:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private String name;
    private int age;
    private Address address;

    public Person clone(){
        Person person = null;
        try {
            // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            // 将流序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            person = (Person) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;

    }

    public static void main(String[] args){
//        Person person = new Person("amy", 13, new Address("shanghai", "122"));
//        Person person1 = person.clone();
//        System.out.println(person);
//        System.out.println(person1);
//        System.out.println(person == person1);
//        System.out.println(person.getAddress() == person1.getAddress());
        System.out.println(3*0.1);
        System.out.println(3*0.1 == 0.3);
        System.out.println(0.3 == 0.3);
    }
}
