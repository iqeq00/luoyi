package com.luoyi.example.jd.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListTest {

    public static void main(String[] args) {

        Object o = null;
        List<String> list = new ArrayList<>();
        System.out.println(ObjectUtil.isNotNull(o));
        System.out.println(ObjectUtil.isNotNull(list));
    }
}
