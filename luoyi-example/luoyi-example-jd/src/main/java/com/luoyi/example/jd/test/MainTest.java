package com.luoyi.example.jd.test;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjUtil;
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("150201778701797670789120");
        list.forEach(System.out::println);

        System.out.println(CollectionUtil.isEmpty(list));

        List<Object> list1 = new ArrayList<>();
        System.out.println(CollectionUtil.isEmpty(list1));
        System.out.println(CollectionUtil.isEmpty(list));
        System.out.println(list1);
        System.out.println("=====");
        Map<String, Object> map = new HashMap<>();
        map.put("11", new Object());
        System.out.println(ObjUtil.isNull(map));



    }
}
