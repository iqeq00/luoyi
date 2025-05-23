package com.luoyi.example.jd.hash;


import lombok.Data;

@Data
public class Animal {
    private int age;
    private String color;

    public Animal(int age, String color) {
        this.age = age;
        this.color = color;
    }

    // 父类方法
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}
