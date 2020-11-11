package org.example.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/5 17:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Cloneable{

    private String name;
    private int age;
    private Address address;

    public Person clone(){
        try {
            Person person = (Person) super.clone();
            person.setAddress(this.getAddress().clone());
            return person;
        }catch (CloneNotSupportedException e){
            return null;
        }

    }

    public static void main(String[] args){
        val person = new Person("amy", 13, new Address("shanghai", "122"));
        Person person1 = person.clone();
        System.out.println(person);
        System.out.println(person1);
        System.out.println(person == person1);
        System.out.println(person.getAddress() == person1.getAddress());
    }
}
