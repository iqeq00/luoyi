package com.luoyi.example.jd.test;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("150201778701797670789120");
        list.forEach(System.out::println);

        System.out.println(CollectionUtils.isEmpty(list));

    }
}
