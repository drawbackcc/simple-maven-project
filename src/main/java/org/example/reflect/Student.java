package org.example.reflect;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/10/31 0:48
 */
public class Student implements Speakable{
    @Override
    public String say(String message) {
        System.out.println("student says " + message);
        return "OK";
    }
}
