package com.luoyi.example.jd.test;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class MianTest4 {

    public static void main(String[] args) {
        List<String> elements = List.of("Element1", "Element2", "Element3", "Element4");

//        Object[] args1 = new Object[5];
//        args1[0] = LocalDateTime.now();
//        args1[1] = "1111";
//        args1[2] = 222;
//        args1[3] = elements;

        System.out.println(StrUtil.join(",", new Test("123")));
    }

    @Data
    @AllArgsConstructor
    public static class Test {

        public String abc;


    }
}
