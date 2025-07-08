package com.luoyi.example.rsa.main.filter;

import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.stream.Stream;

public class MainTest {

    public static void main(String[] args) {

        Personal personal = new Personal();
        personal.setAge(18);
        personal.setName("张三");

        Personal personal1 = new Personal();
        personal1.setAge(24);
        personal1.setName("李四");

        Personal personal2 = new Personal();
        personal2.setAge(30);
        personal2.setName("王五");
        List<Personal> list = List.of(personal, personal1, personal2);

        list.stream().filter(p -> p.getAge() != null)
                .filter(p -> {
                    if (StrUtil.isNotBlank(p.getName())) {
                        return p.getName().equals("李四1");
                    }
                    return true;
                }).findFirst().orElseThrow(() -> new RuntimeException("运行时异常"));
    }
}
