package com.luoyi.example.jd.hash;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode // 调用父类的equals和hashCode方法
public class Cat extends Animal {
    private String furType; // 猫咪的毛发类型

    public Cat(int age, String color, String furType) {
        super(age, color);
        this.furType = furType;
    }

    @Override
    public void makeSound() {
        System.out.println("Cat meows: Meow meow!");
    }
}