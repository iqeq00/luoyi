package com.luoyi.example.jd.hash;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode // 调用父类的equals和hashCode方法
public class Dog extends Animal {
    private String breed; // 狗狗的品种

    public Dog(int age, String color, String breed) {
        super(age, color);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Dog barks: Woof woof!");
    }
}
