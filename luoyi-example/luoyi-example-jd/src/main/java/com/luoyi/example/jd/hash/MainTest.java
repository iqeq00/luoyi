package com.luoyi.example.jd.hash;

public class MainTest {

    public static void main(String[] args) {

        Animal dog = new Dog(2, "blue", "bread");
        System.out.println(dog.hashCode());

        Animal cat = new Cat(2, "blue", "bread11");
        System.out.println(cat.hashCode());
    }
}
