package com.luoyi.example.jd.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonTest {

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();

        Person person1 = new Person();
        person1.setName("zhangsan");
        person1.setAge("18");

        Person person2 = new Person();
        person2.setName("lisi");
        person2.setAge("18");

        Person person3 = new Person();
        person3.setName("wangwu");
        person3.setAge("25");

        Person person4 = new Person();
        person4.setName("zhaoliu");
        person4.setAge("25");

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);

        Map<String, List<Person>> map = list.stream().collect(Collectors.groupingBy(Person::getAge));
        map.entrySet().stream().forEach(entry -> {
            String key = entry.getKey();
            List<Person> persons = entry.getValue();
            System.out.println("key=" + key + ", persons=" + persons);
        });

        list.forEach(var ->{
            if(var.getAge().equals("18")){
                var.setCompany("china");
            }else {
                var.setCompany("japan");
            }
        });
        System.out.println(map);
        System.out.println(list);
        System.out.println("=====");
        Map<String, List<Person>> map1 = list.stream().collect(Collectors.groupingBy(Person::getCompany));
        map1.entrySet().stream().forEach(entry -> {
            String key = entry.getKey();
            List<Person> persons = entry.getValue();
            System.out.println("key=" + key + ", persons=" + persons + ", company=" + entry.getValue());

        });
    }
}
